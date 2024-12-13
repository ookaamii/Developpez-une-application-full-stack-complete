package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
