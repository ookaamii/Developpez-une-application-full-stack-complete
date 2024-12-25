package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de {@link PostService}.
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public PostResponseDTO getPost(Long id) {
        // Récupérer l'article par son id
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article non trouvé"));

        // Récupérer le nom de l'utilisateur par l'user du post
        String username = post.getUser().getUsername();

        // Utiliser le mapper pour convertir Post en DTO avec le nom de l'utilisateur
        return postMapper.postToPostResponseDTO(post, username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO create(PostRequestDTO postDTO) {
        // Convertir le DTO en Post
        Post post = postMapper.postDTOToPost(postDTO);

        // Récupérer l'utilisateur authentifié
        User user = userService.getAuthenticatedUser();

        // Récupérer le thème à partir de topicId de l'article
        Topic topic = topicRepository.findById(postDTO.getTopicId())
                .orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        // Définir les valeurs associées
        post.setTopic(topic); // L'objet Topic (thème)
        post.setUser(user); // L'objet User

        // Enregistrer l'entitée article en bdd
        postRepository.save(post);

        return new ResponseDTO("L'article a été créé avec succès !");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PostResponseDTO> findAllByTopics(String sortDirection) {
        // Récupérer l'utilisateur authentifié
        User user = userService.getAuthenticatedUser();

        // Définir l'ordre de tri
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by("id").descending()
                : Sort.by("id").ascending();
        // Récupérer tous les articles qui ont le thème où l'utilisateur est abonné
        List<Post> posts = postRepository.findAllByTopicSubscriptions(user.getId(), sort);

        return postMapper.postsToPostResponseDTO(posts);
    }

}
