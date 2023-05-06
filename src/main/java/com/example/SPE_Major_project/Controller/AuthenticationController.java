package com.example.SPE_Major_project.Controller;

import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Service.AuthenticationService;
import com.example.SPE_Major_project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.SPE_Major_project.Service.OtpService.generateOTP;
import static com.example.SPE_Major_project.Service.OtpService.sendOTP;

@RestController
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;

    private final UserService userService;

    @PostMapping("/validate-otp-register")
    public boolean register(@RequestBody User user,@RequestParam String otp) {

        return authenticationService.register(user,otp);
    }

    @PostMapping("/send-otp")
    public boolean sendSms(@RequestParam String phoneNumber)
    {
        if(phoneNumber==null)
        {
            return false;
        }
        Integer otp = generateOTP(phoneNumber);
        System.out.println(otp);
        Integer re = sendOTP(phoneNumber, otp.toString());
        System.out.println(re);
        return true;
    }


    @PostMapping("/login")
    public User loginPatient(@RequestBody UserDto userDto){
        return authenticationService.loginUser(userDto);
    }

    @PostMapping("/forget-password")
    public boolean forgetPassword(@RequestParam String phoneNumber)
    {
        return authenticationService.sendOtpForForgetPassword(phoneNumber);
    }

    @PostMapping("/otp-verification-for-change-password")
    public boolean otpVerificationForResetPassword(@RequestParam String phoneNumber,@RequestParam String otp )
    {
        return authenticationService.otpVerification(phoneNumber,otp);
    }

    @PostMapping("/change-password")
    public boolean changePassword(@RequestParam String phoneNumber,@RequestParam String newPassword)
    {
        return authenticationService.changePassword(phoneNumber,newPassword);
    }


}
