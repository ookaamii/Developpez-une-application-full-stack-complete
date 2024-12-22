package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO getPost(Long id);

    ResponseDTO create(PostRequestDTO postDTO);

    List<PostResponseDTO> findAllByTopics(String sort);

}
