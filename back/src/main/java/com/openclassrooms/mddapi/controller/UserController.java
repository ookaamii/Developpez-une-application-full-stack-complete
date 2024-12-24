package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.request.PasswordUpdateDTO;
import com.openclassrooms.mddapi.dto.response.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
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

    @PutMapping("/update/password")
    public ResponseEntity<ResponseDTO> updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO) {
        return ResponseEntity.ok(userService.updatePassword(passwordUpdateDTO));
    }


}
