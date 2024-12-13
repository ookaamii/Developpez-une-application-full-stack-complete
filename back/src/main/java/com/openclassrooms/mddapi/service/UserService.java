package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.AuthDTO;

public interface UserService {

    AuthDTO register(RegisterDTO registerDTO);

    AuthDTO login(LoginDTO loginDTO);
}
