package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Représente un commentaire dans la base de données.
 */
@AllArgsConstructor // Pour créer un constructeur avec tous les champs
@NoArgsConstructor // Pour créer un constructeur sans paramètres
@Data // Crée automatiquement les getters et setters grâce à Lombok
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * Identifiant unique du commentaire
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenu du commentaire qui ne doit pas être null et doit être en format texte
     */
    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * Relation Many-to-One avec l'entité Post
     * Un commentaire est associé à un seul article
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * Identifiant de l'utilisateur qui a créé le commentaire
     */
    @Column(name = "user_id", insertable = false, updatable = false) // Indique que ce champ est uniquement en lecture
    private Long userId;

    /**
     * Relation Many-to-One avec l'entité User
     * Un commentaire est associé à un seul utilisateur (l'auteur du commentaire)
     */
    @ManyToOne(fetch = FetchType.LAZY) // Relation JPA avec l'entité User
    @JoinColumn(name = "user_id") // Jointure sur la colonne user_id
    private User user;

    /**
     * Date de création du commentaire qui s'enregistre automatiquement
     * Elle ne peut pas être modifiée après la création
     */
    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}