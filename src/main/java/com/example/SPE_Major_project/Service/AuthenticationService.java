package com.example.SPE_Major_project.Service;

import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import org.springframework.stereotype.Component;


@Component
public interface AuthenticationService {
    int register(User user,String otp);

 //   boolean otpForRegistration(String phoneNumber);

    User loginUser(UserDto userDto);

   boolean sendOtpForForgetPassword(String phoneNumber);

   boolean changePassword(String phoneNumber,String newPassword);
    boolean otpVerification(String phoneNumber,String otp);
}
