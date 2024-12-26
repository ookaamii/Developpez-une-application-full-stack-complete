package com.openclassrooms.mddapi.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les données "réponse" d'un thème.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TopicDTO {

    /**
     * Identifiant du thème
     */
    private Long id;

    /**
     * Nom de thème
     */
    @NotNull
    private String topic;

    /**
     * Description du thème
     */
    private String description;

}