package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.configuration.exception.BadRequestException;
import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.configuration.exception.UnauthorizedException;
import com.openclassrooms.mddapi.dto.request.PasswordUpdateDTO;
import com.openclassrooms.mddapi.dto.response.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.AuthDTO;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtUtils;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public AuthDTO register(RegisterDTO registerDTO) {
        // Vérifier que l'email ne soit pas déjà utilisé
        validateRegisterData(registerDTO);

        // Mapping du DTO en User
        User user = userMapper.userRegisterDTOToUser(registerDTO);
        // Encoder le mot de passe
        user.setPassword(encoder.encode(user.getPassword()));

        // Enregistrer l'entitée User en bdd
        userRepository.save(user);

        return new AuthDTO(jwtUtils.generateToken(user.getEmail()));
    }

    @Override
    public AuthDTO login(LoginDTO loginDTO) {
        // Authentifier l'utilisateur avec le mail et le mot de passe
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            return new AuthDTO(jwtUtils.generateToken(loginDTO.getEmail()));

        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Identification incorrecte");
        }
    }

    @Override
    public User getAuthenticatedUser() {
        // Récupérer les infos de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
    }

    @Override
    public UserUpdateDTO getProfile() {
        // Récupérer l'utilisateur connecté
        User user = getAuthenticatedUser();

        return userMapper.userToUserDTO(user);
    }

    @Override
    public ResponseDTO update(UserUpdateDTO userDTO) {
        // Récupérer l'utilisateur connecté
        User user = getAuthenticatedUser();

        // Mettre à jour les champs nécessaires de l'utilisateur
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        // Enregistrer les modifications en bdd
        userRepository.save(user);

        return new ResponseDTO("Votre profil a été modifié avec succès !");
    }

    private void validateRegisterData(RegisterDTO registerDTO) {
        // Vérifier que l'email ne soit pas déjà utilisé
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new BadRequestException("Un utilisateur avec cet email existe déjà.");
        }
    }

    @Override
    public ResponseDTO updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        // Récupérer l'utilisateur connecté
        User user = getAuthenticatedUser();

        // Vérifier que l'ancien mot de passe est correct
        if (!encoder.matches(passwordUpdateDTO.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("L'ancien mot de passe est incorrect.");
        }

        // Encoder et mettre à jour le nouveau mot de passe
        String newPassword = encoder.encode(passwordUpdateDTO.getNewPassword());
        user.setPassword(newPassword);

        userRepository.save(user);

        return new ResponseDTO("Votre mot de passe a été mis à jour !");
    }
}
