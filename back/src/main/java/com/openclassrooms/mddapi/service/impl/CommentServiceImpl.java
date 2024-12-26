package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de {@link CommentService}.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final PostRepository postRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommentResponseDTO> findAllByPostId(Long id) {
        // Récupérer tous les commentaires d'un article
        List<Comment> comments = commentRepository.findAllByPostId(id);

        // Pour chaque commentaire, envoyer aussi en réponse l'auteur du commentaire + mapping en DTO
        return comments.stream()
                .map(comment -> {
                    CommentResponseDTO commentDTO = commentMapper.commentToCommentDTO(comment);
                    commentDTO.setAuthor(comment.getUser().getUsername()); // Utilisation directe de l'objet User
                    return commentDTO;
                })
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO create(Long id, CommentRequestDTO commentDTO) {
        // Récupérer l'utilisateur authentifié
        User user = userService.getAuthenticatedUser();

        // Récupérer l'article associé à l'id
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article non trouvé"));

        // Mapper les données du DTO vers l'entité Comment
        Comment comment = commentMapper.commentDTOToComment(commentDTO);

        // Définir les valeurs associées
        comment.setPost(post); // L'objet Post (article)
        comment.setUser(user); // L'objet User

        // Enregistrer l'entitée commentaire en bdd
        commentRepository.save(comment);

        return new ResponseDTO("Votre commentaire a été créé avec succès !");
    }

}
