package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.UserUpdateDTO;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mappe tous les champs de l'entité User vers UserDTO
    UserUpdateDTO userToUserDTO(User user);

    User userRegisterDTOToUser(RegisterDTO registerDTO);

}