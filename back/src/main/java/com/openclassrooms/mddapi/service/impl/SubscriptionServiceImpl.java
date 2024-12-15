package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final TopicRepository topicRepository;

    private final UserService userService;

    @Override
    public ResponseDTO subTopic(Long id) {
        // Récupérer l'objet Topic à partir de topicId
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("L'article " + id + " n'existe pas."));

        Subscription subscription = new Subscription();
        subscription.setTopic(topic);

        // Récupérer l'utilisateur connecté
        User user = userService.getAuthenticatedUser();
        subscription.setUser(user);

        subscriptionRepository.save(subscription);
        return new ResponseDTO("Vous vous êtes abonné avec succès !");
    }

    @Override
    public ResponseDTO unsubscribeTopic(Long id) {

        subscriptionRepository.deleteById(id);
        return new ResponseDTO("Vous vous êtes désabonné avec succès !");

    }

}
