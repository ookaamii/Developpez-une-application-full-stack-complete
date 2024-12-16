package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/{id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<List<CommentResponseDTO>> findAllByPost(@PathVariable Long id) {
        List<CommentResponseDTO> comments = commentService.findAllByPostId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@PathVariable Long id, @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.create(id, commentDTO));
    }

}
