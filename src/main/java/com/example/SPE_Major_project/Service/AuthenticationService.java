package com.example.SPE_Major_project.Service;

import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import org.springframework.stereotype.Component;


@Component
public interface AuthenticationService {
    String register(UserDetailsAndOtpDto userDetailsAndOtpDto);

    void otpForRegistration(String phoneNumber);

    boolean loginUser(UserDto userDto);

    String sendOtpForForgetPassword(String phoneNumber);

    String changePassword(String phoneNumber,String newPassword);

    String otpVerification(String phoneNumber,String otp);
}
