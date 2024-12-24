package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/topic/{id}/sub")
    public ResponseEntity<?> subscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.subTopic(id));
    }

    @PostMapping("/topic/unsubscribe/{id}")
    public ResponseEntity<ResponseDTO> unsubscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.unsubscribeTopic(id));
    }

    @GetMapping("/me/subscriptions")
    public ResponseEntity<List<TopicDTO>> findAllByUser() {
        return ResponseEntity.ok(subscriptionService.findAllByUser());
    }

}
