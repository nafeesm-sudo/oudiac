package com.app.oudiac.services.adminService;

import com.app.oudiac.dtos.userDtos.UserRegisterRequestDto;
import com.app.oudiac.dtos.userDtos.UserRegisterResponseDto;
import com.app.oudiac.exceptions.UserAlreadyExitException;
import com.app.oudiac.models.User;
import com.app.oudiac.models.enums.Role;
import com.app.oudiac.repositories.UserRepository;
import com.app.oudiac.services.emailOtpService.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    //Register Admin
    public ResponseEntity<UserRegisterResponseDto> registerAdmin(UserRegisterRequestDto user) {
        //Validation done
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExitException("Email already in use");
        }

        User newUser=UserRegisterRequestDto.fromUserRegisterRequestDtoToUser(user);
//        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.ADMIN);
        userRepository.save(newUser);

        otpService.sendOtp(user.getEmail());

        UserRegisterResponseDto responseDto=new  UserRegisterResponseDto();
        responseDto.setMessage("Registration Successful");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
