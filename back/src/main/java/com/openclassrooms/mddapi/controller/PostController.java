package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody PostRequestDTO postDTO) {
        return ResponseEntity.ok(postService.create(postDTO));
    }

}
