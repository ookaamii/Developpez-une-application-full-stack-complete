package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Représente un utilisateur dans la base de données.
 */
@AllArgsConstructor // Pour créer un constructeur avec tous les champs
@NoArgsConstructor // Pour créer un constructeur sans paramètres
@Data // Crée automatiquement les getters et setters grâce à Lombok
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    /**
     * Identifiant unique de l'utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username de l'utilisateur qui ne doit pas être null
     */
    @Column(nullable = false)
    private String username;

    /**
     * Email de l'utilisateur qui ne doit pas être null
     */
    @Column(nullable = false)
    private String email;

    /**
     * Mot de passe de l'utilisateur qui ne doit pas être null
     */
    @Column(nullable = false)
    private String password;

    /**
     * Date de création de l'utilisateur qui s'enregistre automatiquement
     * Elle ne peut pas être modifiée après la création
     */
    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Date de modification de l'utilisateur qui s'enregistre automatiquement
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}