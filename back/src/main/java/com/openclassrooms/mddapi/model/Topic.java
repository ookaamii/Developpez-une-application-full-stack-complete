package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un thème dans la base de données.
 */
@AllArgsConstructor // Pour créer un constructeur avec tous les champs
@NoArgsConstructor // Pour créer un constructeur sans paramètres
@Data // Crée automatiquement les getters et setters grâce à Lombok
@Entity
@Table(name = "topics")
public class Topic {

    /**
     * Identifiant unique du thème
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre du thème qui ne doit pas être null
     */
    @Column(nullable = false)
    private String topic;

    /**
     * Description du thème qui ne doit pas être null
     */
    @Column(nullable = false)
    private String description;

}