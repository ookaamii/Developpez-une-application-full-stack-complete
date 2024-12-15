package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    List<CommentResponseDTO> commentsToCommentsDTO(List<Comment> comments);

    CommentResponseDTO commentToCommentDTO(Comment comment);

    Comment commentDTOToComment(CommentRequestDTO commentDTO);


}
