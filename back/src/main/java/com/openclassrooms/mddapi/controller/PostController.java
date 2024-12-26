package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les articles.
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * Affiche tous les articles par thème où l'utilisateur est abonné.
     *
     * @param sort L'ordre par l'id dans lequel les données doivent être triées.
     * @return La liste des articles.
     */
    @GetMapping("/")
    public ResponseEntity<List<PostResponseDTO>> findAllByTopics(@RequestParam(defaultValue = "desc") String sort) {
        return ResponseEntity.ok(postService.findAllByTopics(sort));
    }

    /**
     * Affiche un article.
     *
     * @param id L'identifiant de l'article.
     * @return L'article.
     * @throws NotFoundException Si l'article n'existe pas.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    /**
     * Enregistre un nouvel article.
     *
     * @param postDTO Données de l'article à enregistrer.
     * @return Réponse de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody PostRequestDTO postDTO) {
        return ResponseEntity.ok(postService.create(postDTO));
    }

}
