package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.AuthDTO;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtUtils;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        validateRegisterData(registerDTO);

        User user = userMapper.userRegisterDTOToUser(registerDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return new AuthDTO(jwtUtils.generateToken(user.getEmail()));
    }

    @Override
    public AuthDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        return new AuthDTO(jwtUtils.generateToken(loginDTO.getEmail()));
    }

    private void validateRegisterData(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }
    }

}
