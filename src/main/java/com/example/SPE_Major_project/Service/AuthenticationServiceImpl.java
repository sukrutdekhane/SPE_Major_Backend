package com.example.SPE_Major_project.Service;



import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Repository.AuthenticationRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.management.modelmbean.ModelMBeanInfo;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.SPE_Major_project.Service.OtpService.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{


    private static final Integer EXPIRE_MIN = 5;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationRepository authenticationRepository;

    private static LoadingCache<String, Integer> otpCache;


    public boolean register(User request,String otp)
    {
        String pto = getOPTByKey(request.getMobileNumber());

        if(!otp.equals(pto))
        {
            return false;
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = authenticationRepository.save(user);
        log.info("User Registered");
        return true;
    }


    public boolean otpForForgetPassword(String phoneNumber)
    {
        Integer otp = generateOTP(phoneNumber);
        System.out.println(otp);
        Integer re = forgotOtp(phoneNumber, otp.toString());
        return true;
    }




    @Override
    public User loginUser(UserDto userDto)
    {
        //User user =null;
        User user=authenticationRepository.findByEmail(userDto.getEmail()).get();
        if (user != null)
        {
            String password = userDto.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password,encodedPassword);
            if (isPwdRight)
            {
                log.info("User Logged In");
                return user;
            } else
            {
                log.error("Wrong password");
                return null;
            }
        }
        log.error("User not Found");
        return null;
    }

    @Override
    public boolean sendOtpForForgetPassword(String phoneNumber)
    {
        User user=authenticationRepository.findByMobileNumber(phoneNumber);
        if(user!=null)
        {

            return otpForForgetPassword(phoneNumber);
        }
        return false;

    }

    @Override
    public boolean otpVerification(String phoneNumber, String otp) {

        String pto = getOPTByKey(phoneNumber);
        if(!otp.equals(pto))
        {
            return false;
        }
        return true;
    }


    @Override
    public boolean changePassword(String mobileNumber,String newPassword) {
         if(newPassword==null)return false;

         String encodedOldPassword=authenticationRepository.getOldPassword(mobileNumber);
         if(passwordEncoder.matches(newPassword,encodedOldPassword))// if new and old password matches
         {
             return false;
         }
         else
         {
             authenticationRepository.setNewPassword(mobileNumber,passwordEncoder.encode(newPassword));
         }

         return true;

    }


}
