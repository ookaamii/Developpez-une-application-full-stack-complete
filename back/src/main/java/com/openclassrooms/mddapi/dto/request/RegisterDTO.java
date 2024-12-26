package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les données d'enregistrement d'un utilisateur.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterDTO {

    /**
     * Email de l'utilisateur
     */
    @NotBlank
    @Email
    private String email;

    /**
     * Username de l'utilisateur
     */
    @NotBlank
    private String username;

    /**
     * Mot de passe de l'utilisateur qui doit respecter des contraintes (contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial,
     * et un minimum de 8 caractères)
     */
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$")
    private String password;

}