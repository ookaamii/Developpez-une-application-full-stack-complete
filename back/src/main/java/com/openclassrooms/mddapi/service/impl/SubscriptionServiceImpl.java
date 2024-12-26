package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de {@link SubscriptionService}.
 */
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final TopicMapper topicMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO subTopic(Long id) {
        // Récupérer le thème à partir de l'id
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        // Créer un nouvel abonnement
        Subscription subscription = new Subscription();

        // Récupérer l'utilisateur connecté
        User user = userService.getAuthenticatedUser();

        // Définir les valeurs associées
        subscription.setTopic(topic); // L'objet Topic (thème)
        subscription.setUser(user); // L'objet User

        // Enregistrer l'entitée abonnement en bdd
        subscriptionRepository.save(subscription);

        return new ResponseDTO("Vous vous êtes abonné avec succès !");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO unsubscribeTopic(Long id) {
        // Récupérer le thème à partir de l'id
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        // Récupérer l'utilisateur connecté
        User user = userService.getAuthenticatedUser();

        // Supprimer un abonnement
        subscriptionRepository.deleteSubscriptionByTopicAndUser(topic, user);

        return new ResponseDTO("Vous vous êtes désabonné avec succès !");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicDTO> findAllByUser() {
        // Récupérer l'utilisateur connecté
        User user = userService.getAuthenticatedUser();

        // Récupérer la liste des abonnements de l'utilisateur connecté
        List<Subscription> subs = subscriptionRepository.findAllByUser(user);

        // Pour chaque abonnement, récupérer le thème à partir de topicId + mapping en DTO
        return subs.stream()
                .map(sub -> {
                    Topic topic = topicRepository.findById(sub.getTopic().getId())
                            .orElseThrow(() -> new NotFoundException("Thème non trouvé"));
                    TopicDTO topicDTO = topicMapper.topicToTopicDTO(topic);
                    return topicDTO;
                })
                .toList();
    }

}
