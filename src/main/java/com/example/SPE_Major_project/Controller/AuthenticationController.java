package com.example.SPE_Major_project.Controller;

import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Service.AuthenticationService;
import com.example.SPE_Major_project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController
{
    private final AuthenticationService authenticationService;

    private final UserService userService;

    @PostMapping("/validate-otp-register")
    public String register(@RequestBody UserDetailsAndOtpDto userDetailsAndOtpDto) {

        String status= authenticationService.register(userDetailsAndOtpDto);
        return status;
    }

    @PostMapping("/send-otp")
    public void sendSms(@RequestParam String phoneNumber)
    {
        System.out.println(phoneNumber);
        authenticationService.otpForRegistration(phoneNumber);
    }


    @PostMapping("/login")
    public boolean loginPatient(@RequestBody UserDto userDto){
        return authenticationService.loginUser(userDto);
    }

    @PostMapping("/forget-password")
    public String forgetPassword(@RequestParam String phoneNumber)
    {
        return authenticationService.sendOtpForForgetPassword(phoneNumber);
    }

    @PostMapping("/otp-verification-for-change-password")
    public String otpVerificationForResetPassword(@RequestParam String phoneNumber,@RequestParam String otp )
    {
        return authenticationService.otpVerification(phoneNumber,otp);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String phoneNumber,@RequestParam String newPassword)
    {
        return authenticationService.changePassword(phoneNumber,newPassword);
    }
}
