package com.openclassrooms.mddapi.dto.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les données "réponse" modifiées d'un utilisateur .
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserUpdateDTO {

    /**
     * Identifiant de l'utilisateur
     */
    private Long id;

    /**
     * Email de l'utilisateur
     */
    @Email
    private String email;

    /**
     * Username de l'utilisateur
     */
    private String username;

}
