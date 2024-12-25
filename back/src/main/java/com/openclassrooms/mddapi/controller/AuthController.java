package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.configuration.exception.BadRequestException;
import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.configuration.exception.UnauthorizedException;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.security.JwtUtils;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur pour gérer l'authentification utilisateur.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    private final UserServiceImpl userServiceImpl;

    /**
     * Enregistre un nouvel utilisateur.
     *
     * @param registerDTO Données de l'utilisateur à enregistrer.
     * @return Réponse avec un token JWT.
     * @throws BadRequestException Si l'email est déjà utilisé.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(userServiceImpl.register(registerDTO));
    }

    /**
     * Connecte un utilisateur.
     *
     * @param loginDTO Données de l'utilisateur à connecter.
     * @return Réponse avec un token JWT.
     * @throws UnauthorizedException Si les identifiants sont incorrects.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userServiceImpl.login(loginDTO));
    }

}
