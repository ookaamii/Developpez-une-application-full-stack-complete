package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;

import java.util.List;

/**
 * Service pour gérer les abonnements des utilisateurs.
 */
public interface SubscriptionService {

    /**
     * Permet à un utilisateur de s'abonner à un thème.
     *
     * @param id L'identifiant du thème.
     * @return Un message de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    ResponseDTO subTopic(Long id);

    /**
     * Permet à un utilisateur de se désabonner d'un thème.
     *
     * @param id L'identifiant du thème.
     * @return Un message de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    ResponseDTO unsubscribeTopic(Long id);

    /**
     * Permet d'afficher les thèmes où l'utilisateur est abonné.
     *
     * @return La liste des thèmes.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    List<TopicDTO> findAllByUser();

}
