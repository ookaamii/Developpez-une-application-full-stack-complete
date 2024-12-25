package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un abonnement (un utilisateur qui s'est inscrit à un thème) dans la base de données.
 */
@AllArgsConstructor // Pour créer un constructeur avec tous les champs
@NoArgsConstructor // Pour créer un constructeur sans paramètres
@Data // Crée automatiquement les getters et setters grâce à Lombok
@Entity
@Table(name = "subscriptions")
public class Subscription {

    /**
     * Identifiant unique de l'abonnement
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relation Many-to-One avec l'entité Topic
     * Un abonnement est associé à un seul thème
     */
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    /**
     * Relation Many-to-One avec l'entité User
     * Un abonnement est associé à un seul utilisateur (qui s'est abonné au thème)
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}