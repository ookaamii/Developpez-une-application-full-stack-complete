package com.openclassrooms.mddapi.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TopicDTO {

    private Long id;

    @NotNull
    private String topic;

    private String description;

}