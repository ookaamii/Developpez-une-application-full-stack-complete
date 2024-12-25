package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les commentaires des articles.
 */
@RestController
@RequestMapping("/post/{id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Affiche tous les commentaires d'un article.
     *
     * @param id Identifiant de l'article associé.
     * @return La liste des commentaires et leur auteur directement.
     */
    @GetMapping("/")
    public ResponseEntity<List<CommentResponseDTO>> findAllByPost(@PathVariable Long id) {
        List<CommentResponseDTO> comments = commentService.findAllByPostId(id);
        return ResponseEntity.ok(comments);
    }

    /**
     * Enregistre un nouveau commentaire.
     *
     * @param id Identifiant de l'article associé.
     * @param commentDTO Données du commentaire à enregistrer.
     * @return Réponse de succès.
     * @throws NotFoundException Si l'article n'existe pas.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO commentDTO) {
        return ResponseEntity.ok(commentService.create(id, commentDTO));
    }

}
