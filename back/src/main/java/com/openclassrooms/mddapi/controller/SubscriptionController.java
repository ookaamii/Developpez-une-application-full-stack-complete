package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les abonnements utilisateur.
 */
@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    /**
     * Abonne un utilisateur à un thème.
     *
     * @param id L'identifiant du thème.
     * @return Un message de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    @PostMapping("/topic/{id}/sub")
    public ResponseEntity<?> subscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.subTopic(id));
    }

    /**
     * Désabonne un utilisateur d'un thème.
     *
     * @param id L'identifiant du thème.
     * @return Un message de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    @PostMapping("/topic/{id}/unsubscribe")
    public ResponseEntity<ResponseDTO> unsubscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.unsubscribeTopic(id));
    }

    /**
     * Affiche les thèmes auxquels l'utilisateur est abonné.
     *
     * @return La liste des thèmes.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    @GetMapping("/me/subscriptions")
    public ResponseEntity<List<TopicDTO>> findAllByUser() {
        return ResponseEntity.ok(subscriptionService.findAllByUser());
    }

}
