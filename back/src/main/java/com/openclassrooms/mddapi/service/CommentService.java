package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;

import java.util.List;

public interface CommentService {

    List<CommentResponseDTO> findAllByPostId(Long id);

    ResponseDTO create(Long id, CommentRequestDTO commentRequestDTO);

}
