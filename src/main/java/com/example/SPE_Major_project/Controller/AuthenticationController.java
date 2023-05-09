package com.example.SPE_Major_project.Controller;

import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Repository.AuthenticationRepository;
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
    private final AuthenticationRepository authenticationRepository;

    private final UserService userService;

    @PostMapping("/validate-otp-register")
    public int register(@RequestBody User user,@RequestParam String otp) {

        return authenticationService.register(user,otp);
    }

    @PostMapping("/send-otp")
    public int sendSms(@RequestParam String phoneNumber)
    {
        if(phoneNumber==null)
        {
            return 0;
        }
        User existingUser=authenticationRepository.findByMobileNumber(phoneNumber);
        if(existingUser!=null) return 2;// you already have account with this mobile number

        Integer otp = generateOTP(phoneNumber);
        System.out.println(otp);
        Integer re = sendOTP(phoneNumber, otp.toString());
        System.out.println(re);
        return 1;//new account
    }

    @PostMapping("/send-otp-for-forgot-password")
    public int sendOtp(@RequestParam String phoneNumber)
    {
        if(phoneNumber==null)
        {
            return 0; //invalid phone number
        }
        User existingUser=authenticationRepository.findByMobileNumber(phoneNumber);
        if(existingUser==null) return 2; //user not exist
        Integer otp = generateOTP(phoneNumber);
        System.out.println(otp);
        Integer re = sendOTP(phoneNumber, otp.toString());
        System.out.println(re);
        return 1;
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
