package com.openclassrooms.mddapi.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente les données d'enregistrement d'un article.
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class PostRequestDTO {

    /**
     * Identifiant de l'article
     */
    private Long id;

    /**
     * Titre de l'article
     */
    @NotBlank
    private String title;

    /**
     * Contenu de l'article
     */
    @NotBlank
    private String content;

    /**
     * Identifiant du thème associé
     */
    @Column(name = "topic_id")
    private Long topicId;

    /**
     * Identifiant de l'utilisateur associé
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Date de création de l'article qui s'enregistre automatiquement
     */
    private LocalDateTime createdAt;

}
