package com.openclassrooms.mddapi.dto.response;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
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
    private Topic topic;

    @Column(name = "user_id")
    private User user;

    private LocalDateTime createdAt;

}
