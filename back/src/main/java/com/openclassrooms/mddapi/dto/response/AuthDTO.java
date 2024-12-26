package com.openclassrooms.mddapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un jwt token après authentification de l'utilisateur.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthDTO {

    /**
     * JWT token de l'utilisateur connecté
     */
    private String token;

}
