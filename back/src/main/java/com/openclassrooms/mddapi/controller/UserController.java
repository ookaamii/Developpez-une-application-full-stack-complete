package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserUpdateDTO> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> update(@RequestBody UserUpdateDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

}
