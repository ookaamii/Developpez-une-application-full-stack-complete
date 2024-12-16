package com.openclassrooms.mddapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int code;

    private String message;

    private String path;

    private LocalDateTime timestamp;

}
