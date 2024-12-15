package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.dto.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.AuthDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.User;

public interface UserService {

    AuthDTO register(RegisterDTO registerDTO);

    AuthDTO login(LoginDTO loginDTO);

    User getAuthenticatedUser();

    UserUpdateDTO getProfile();

    ResponseDTO update(UserUpdateDTO userDTO);

}
