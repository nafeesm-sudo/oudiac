package com.app.oudiac.controllers;

import com.app.oudiac.dtos.emailDto.EmailOTPRequestDto;
import com.app.oudiac.dtos.userDtos.UserInfoDto;
import com.app.oudiac.services.emailOtpService.OtpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-email-otp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody EmailOTPRequestDto request) {
        otpService.sendOtp(request.getEmail());
        return new ResponseEntity<>("OTP sent to "+request.getEmail(), HttpStatus.OK);
    }

    @PostMapping("/verify-email-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email,
                                                 @RequestParam String otp) {
        return otpService.verifyOtp(email, otp);
    }
}
