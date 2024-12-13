package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String username;

    @JsonIgnore
    @Size(min = 8)
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
