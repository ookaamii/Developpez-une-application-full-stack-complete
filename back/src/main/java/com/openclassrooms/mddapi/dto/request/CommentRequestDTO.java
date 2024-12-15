package com.openclassrooms.mddapi.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CommentRequestDTO {

    private Long id;

    private String content;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime createdAt;

}
