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

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> findAllByPostId(Long id) {
        List<Comment> comments = commentRepository.findAllByPostId(id);

        return comments.stream()
                .map(comment -> {
                    CommentResponseDTO commentDTO = commentMapper.commentToCommentDTO(comment);
                    commentDTO.setAuthor(comment.getUser().getUsername()); // Utilisation directe de l'objet User
                    return commentDTO;
                })
                .toList();
    }

    @Override
    public ResponseDTO create(Long id, CommentRequestDTO commentDTO) {
        // Récupérer l'utilisateur authentifié
        User user = userService.getAuthenticatedUser();

        // Récupérer l'entité Post associée au postId
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Commentaire non trouvé"));

        // Mapper les données du DTO vers l'entité Comment
        Comment comment = commentMapper.commentDTOToComment(commentDTO);

        // Définir les relations et les valeurs associées
        comment.setPost(post); // Associer l'objet Post
        comment.setUser(user); // Associer l'objet User

        commentRepository.save(comment);

        return new ResponseDTO("Votre commentaire a été créé avec succès !");
    }

}
