package com.openclassrooms.mddapi.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class PostResponseDTO {

    private Long id;

    private String title;

    private String author;

    private String content;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime createdAt;

}
