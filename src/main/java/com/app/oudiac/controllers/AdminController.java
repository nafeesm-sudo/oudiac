package com.app.oudiac.controllers;

import com.app.oudiac.dtos.userDtos.UserRegisterRequestDto;
import com.app.oudiac.dtos.userDtos.UserRegisterResponseDto;
import com.app.oudiac.services.adminService.AdminService;
import com.app.oudiac.services.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create Admin
    @PostMapping("/register")   //This mapping is for loging and signup both
    public ResponseEntity<UserRegisterResponseDto> createAdmin(@Valid @RequestBody UserRegisterRequestDto request) {
        return adminService.registerAdmin(request);
    }

}
