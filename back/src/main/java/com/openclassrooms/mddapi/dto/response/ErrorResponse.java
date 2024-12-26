package com.openclassrooms.mddapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente les données "réponse" d'une erreur.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * Code erreur
     */
    private int code;

    /**
     * Message personnalisé de l'erreur
     */
    private String message;

    /**
     * Path où a eu lieu l'erreur
     */
    private String path;

    /**
     * Date et heure du déclenchement de l'erreur
     */
    private LocalDateTime timestamp;

}
