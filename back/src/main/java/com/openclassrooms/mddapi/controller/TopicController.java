package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les thèmes.
 */
@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    /**
     * Affiche tous les thèmes.
     *
     * @return La liste des thèmes.
     */
    @GetMapping("/")
    public ResponseEntity<List<TopicDTO>> findAll() {
        List<TopicDTO> topics = topicService.findAll();
        return ResponseEntity.ok(topics);
    }

}
