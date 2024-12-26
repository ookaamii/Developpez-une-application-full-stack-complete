package com.openclassrooms.mddapi.dto.response;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente les données "réponse" d'un article.
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class PostResponseDTO {

    /**
     * Identifiant d'un article
     */
    private Long id;

    /**
     * Titre d'un article
     */
    private String title;

    /**
     * Username de l'utilisateur qui a créé l'article
     */
    private String author;

    /**
     * Contenu de l'article
     */
    private String content;

    /**
     * Identifiant du thème associé
     */
    @Column(name = "topic_id")
    private Topic topic;

    /**
     * Identifiant de l'utilisateur qui a créé l'article
     */
    @Column(name = "user_id")
    private User user;

    /**
     * Date de création de l'article qui s'enregistre automatiquement
     */
    private LocalDateTime createdAt;

}
