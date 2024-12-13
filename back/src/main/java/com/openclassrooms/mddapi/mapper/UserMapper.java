package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mappe tous les champs de l'entité User vers UserDTO
    UserDTO userToUserDTO(User user);

    // Mappe les champs de UserDTO vers User
    User userDTOToUser(UserDTO userDTO);

    User userRegisterDTOToUser(RegisterDTO registerDTO);

}