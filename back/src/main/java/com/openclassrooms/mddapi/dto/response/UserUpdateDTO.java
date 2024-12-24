package com.openclassrooms.mddapi.dto.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserUpdateDTO {

    private Long id;

    @Email
    private String email;

    private String username;

}
