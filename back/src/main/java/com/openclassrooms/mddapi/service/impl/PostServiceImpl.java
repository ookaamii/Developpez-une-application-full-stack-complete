package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostMapper postMapper;

    private final UserService userService;

    @Override
    public PostResponseDTO getPost(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("L'article " + id + " n'existe pas."));

        // Récupération de l'utilisateur par l'userId du post
        User user = userRepository.findById(post.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur : " + post.getUserId() + " non trouvé"));

        // Utilisation du mapper pour convertir Post en PostResponseDTO avec le nom de l'utilisateur
        return postMapper.postToPostResponseDTO(post, user.getUsername());

    }

    @Override
    public ResponseDTO create(PostRequestDTO postDTO) {

        Post post = postMapper.postDTOToPost(postDTO);
        User user = userService.getAuthenticatedUser();
        post.setUserId(user.getId());
        postRepository.save(post);

        return new ResponseDTO("L'article a été créé avec succès !");

    }

}
