package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les données d'enregistrement d'une connexion utilisateur.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginDTO {

    /**
     * Email de l'utilisateur
     */
    @NotBlank
    @Email
    private String email;

    /**
     * Mot de passe de l'utilisateur
     */
    @NotBlank
    private String password;

}
