package com.app.oudiac.services.emailOtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText(
                "Dear User,\n\n" +
                        "Your One-Time Password (OTP) for verification is: " + otp + ".\n\n" +
                        "This OTP is valid for a limited time. Please use it before it expires.\n\n" +
                        "For your security, do not share this OTP with anyone.\n\n" +
                        "If you did not request this, please ignore this message.\n\n" +
                        "Regards,\n" +
                        "Oudiac.com"
        );
//        message.setText(otp+ " is Your OTP \n\n\n\n Please do not share this to anyone\n\n\n\n verify before expiry");

        mailSender.send(message);
    }
}