package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", source = "username") // Associer le nom d'utilisateur
    PostResponseDTO postToPostResponseDTO(Post post, String username);

    Post postDTOToPost(PostRequestDTO postDTO);

    List<PostResponseDTO> postsToPostResponseDTO(List<Post> posts);

}
