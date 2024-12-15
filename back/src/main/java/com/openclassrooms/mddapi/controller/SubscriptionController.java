package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/sub/{id}")
    public ResponseEntity<?> subscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.subTopic(id));
    }

    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<ResponseDTO> unsubscribe(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.unsubscribeTopic(id));
    }

}
