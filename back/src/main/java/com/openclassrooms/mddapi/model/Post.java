package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Représente un article dans la base de données.
 */
@AllArgsConstructor // Pour créer un constructeur avec tous les champs
@NoArgsConstructor // Pour créer un constructeur sans paramètres
@Data // Crée automatiquement les getters et setters grâce à Lombok
@Entity
@Table(name = "posts")
public class Post {

    /**
     * Identifiant unique de l'article
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre de l'article qui ne doit pas être null
     */
    @Column(nullable = false)
    private String title;

    /**
     * Contenu de l'article qui ne doit pas être null et doit être en format texte
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * Relation Many-to-One avec l'entité Topic
     * Un article est associé à un seul thème
     */
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    /**
     * Relation Many-to-One avec l'entité User
     * Un article est associé à un seul utilisateur (l'auteur de l'article)
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Date de création de l'article qui s'enregistre automatiquement
     * Elle ne peut pas être modifiée après la création
     */
    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}