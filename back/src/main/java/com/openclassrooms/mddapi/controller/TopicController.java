package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/")
    public ResponseEntity<List<TopicDTO>> findAll() {
        List<TopicDTO> topics = topicService.findAll();
        return ResponseEntity.ok(topics);
    }

}
