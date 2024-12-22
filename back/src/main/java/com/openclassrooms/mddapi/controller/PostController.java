package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<PostResponseDTO>> findAllByTopics(@RequestParam(defaultValue = "desc") String sort) {
        return ResponseEntity.ok(postService.findAllByTopics(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody PostRequestDTO postDTO) {
        return ResponseEntity.ok(postService.create(postDTO));
    }

}
