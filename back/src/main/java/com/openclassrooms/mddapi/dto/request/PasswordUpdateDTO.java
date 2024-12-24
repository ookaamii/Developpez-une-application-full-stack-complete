package com.openclassrooms.mddapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PasswordUpdateDTO {

    private String oldPassword;
    private String newPassword;

}
