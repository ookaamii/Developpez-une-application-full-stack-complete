package com.openclassrooms.mddapi.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente les données d'enregistrement d'un commentaire.
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CommentRequestDTO {

    /**
     * Identifiant du commentaire
     */
    private Long id;

    /**
     * Contenu du commentaire
     */
    @NotBlank
    private String content;

    /**
     * L'identifiant de l'article associé
     */
    @Column(name = "post_id")
    private Long postId;

    /**
     * L'identifiant de l'utilisateur qui a créé le commentaire
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Date de création du commentaire qui s'enregistre automatiquement
     */
    private LocalDateTime createdAt;

}
