package com.example.SPE_Major_project.Controller;

import com.example.SPE_Major_project.Dto.AuthenticationResponse;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Repository.AuthenticationRepository;
import com.example.SPE_Major_project.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.SPE_Major_project.Service.OtpService.generateOTP;
import static com.example.SPE_Major_project.Service.OtpService.sendOTP;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;
    private final AuthenticationRepository authenticationRepository;


    //Register
    @PostMapping("/validate-otp-register")
    public int register(@RequestBody User user,@RequestParam String otp) {

        return authenticationService.register(user,otp);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDto userDto
    ) {

        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .token(authenticationService.loginUser(userDto).getToken())
                .build();
        return ResponseEntity.ok(authenticationResponse);
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
