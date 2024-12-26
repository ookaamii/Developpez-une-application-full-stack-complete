package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les données d'enregistrement d'une modification de mot de passe utilisateur.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PasswordUpdateDTO {

    /**
     * Ancien mot de passe de l'utilisateur
     */
    @NotBlank
    private String oldPassword;

    /**
     * Nouveau mot de passe de l'utilisateur qui doit respecter des contraintes (contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial,
     * et un minimum de 8 caractères)
     */
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$")
    private String newPassword;

}
