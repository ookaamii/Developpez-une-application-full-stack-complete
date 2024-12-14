package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserService userService;

    @Override
    public ResponseDTO subTopic(Long id) {
        Subscription subscription = new Subscription();
        subscription.setTopicId(id);

        // Récupérer l'utilisateur connecté
        User user = userService.getAuthenticatedUser();
        subscription.setUserId(user.getId());

        subscriptionRepository.save(subscription);
        return new ResponseDTO("Vous vous êtes abonné avec succès !");
    }

}
