package com.app.oudiac.controllers;

import com.app.oudiac.dtos.userDtos.UserInfoDto;
import com.app.oudiac.dtos.userDtos.UserLoginRequestDto;
import com.app.oudiac.dtos.userDtos.UserRegisterRequestDto;
import com.app.oudiac.dtos.userDtos.UserRegisterResponseDto;
import com.app.oudiac.services.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequestDto request) {
        return userService.login(request);
    }

    // Get user by ID
    @GetMapping("/oudiac/{id}")
    public ResponseEntity<UserInfoDto> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
