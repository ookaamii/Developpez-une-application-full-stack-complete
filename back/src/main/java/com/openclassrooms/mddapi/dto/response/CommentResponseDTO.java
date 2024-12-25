package com.openclassrooms.mddapi.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente les données "réponse" d'un commentaire.
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CommentResponseDTO {

    /**
     * Identifiant du commentaire
     */
    private Long id;

    /**
     * Username de l'utilisateur qui a créé le commentaire
     */
    private String author;

    /**
     * Contenu du commentaire
     */
    private String content;

    /**
     * Identifiant de l'article associé
     */
    @Column(name = "post_id")
    private Long postId;

    /**
     * Identifiant de l'utilisateur qui a créé le commentaire
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Date de création du commentaire qui s'enregistre automatiquement
     */
    private LocalDateTime createdAt;

}
