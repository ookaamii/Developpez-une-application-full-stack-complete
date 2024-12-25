package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.configuration.exception.BadRequestException;
import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.PasswordUpdateDTO;
import com.openclassrooms.mddapi.dto.response.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les utilisateurs.
 */
@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Affiche le profil de l'utilisateur connecté.
     *
     * @return Les informations de l'utilisateur.
     */
    @GetMapping("/")
    public ResponseEntity<UserUpdateDTO> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    /**
     * Modifie l'username et/ou l'email de l'utilisateur.
     *
     * @return Un message de succès.
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody UserUpdateDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     *
     * @return Un message de succès.
     * @throws BadRequestException Si l'ancien mot de passe est incorrect.
     */
    @PutMapping("/update/password")
    public ResponseEntity<ResponseDTO> updatePassword(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        return ResponseEntity.ok(userService.updatePassword(passwordUpdateDTO));
    }


}
