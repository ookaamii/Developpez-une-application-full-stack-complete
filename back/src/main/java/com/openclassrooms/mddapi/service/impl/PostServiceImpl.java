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
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    @Override
    public PostResponseDTO getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article non trouvé"));

        // Récupération de l'utilisateur par l'userId du post
        String username = post.getUser().getUsername();

        // Utilisation du mapper pour convertir Post en PostResponseDTO avec le nom de l'utilisateur
        return postMapper.postToPostResponseDTO(post, username);
    }

    @Override
    public ResponseDTO create(PostRequestDTO postDTO) {
        Post post = postMapper.postDTOToPost(postDTO);
        User user = userService.getAuthenticatedUser();
        // Récupérer l'objet Topic à partir de topicId
        Topic topic = topicRepository.findById(postDTO.getTopicId())
                .orElseThrow(() -> new NotFoundException("Thème non trouvé"));

        // Associer le Topic à l'entité Post
        post.setTopic(topic);
        post.setUser(user);
        postRepository.save(post);

        return new ResponseDTO("L'article a été créé avec succès !");
    }

}
