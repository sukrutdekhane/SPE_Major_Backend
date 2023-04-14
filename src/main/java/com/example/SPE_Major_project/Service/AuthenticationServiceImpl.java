package com.example.SPE_Major_project.Service;



import com.example.SPE_Major_project.Dto.UserDetailsAndOtpDto;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Repository.AuthenticationRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.management.modelmbean.ModelMBeanInfo;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{


    private static final Integer EXPIRE_MIN = 5;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static LoadingCache<String, Integer> otpCache;

    private final Environment env;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationServiceImpl(Environment env, AuthenticationRepository authenticationRepository) {
        this.env = env;
        this.authenticationRepository = authenticationRepository;
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }

    public static void clearOTPFromCache(String key) {
        otpCache.invalidate(key);
    }

    public static String getOPTByKey(String key)
    {
        Integer otp = otpCache.getIfPresent(key);
        System.out.println("cache:"+otp);
        if(otp==null)
            return "-99";
        else return otp.toString();
    }

    public String register(UserDetailsAndOtpDto request)
    {
        System.out.println(request);
        System.out.println(request.getUser().getMobileNumber());
        String pto = getOPTByKey(request.getUser().getMobileNumber());
        System.out.println("PTO: "+pto);
        System.out.println("OTP: "+request.getOtp());

        if(!request.getOtp().equals(pto))
        {
            return "Invalid Otp";
        }
        var user = User.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .mobileNumber(request.getUser().getMobileNumber())
                .email(request.getUser().getEmail())
                .password(passwordEncoder.encode(request.getUser().getPassword()))
                .build();
        var savedUser = authenticationRepository.save(user);
        return("successfully registered New User");
    }

    public void otpForRegistration(String phoneNumber)
    {
        PhoneNumber to = new PhoneNumber("+91"+phoneNumber);
        PhoneNumber from = new PhoneNumber(env.getProperty("app.trial_number"));
        String otp = generateOTP(phoneNumber).toString();
        System.out.println(otp);
        String otpMessage = "Dear User, Your OTP is:" + otp + " Use this to complete your Registration...Thank You";
        Message message = Message.creator(to, from, otpMessage).create();
    }

    public String otpForForgetPassword(String phoneNumber)
    {
        PhoneNumber to = new PhoneNumber("+91"+phoneNumber);
        PhoneNumber from = new PhoneNumber(env.getProperty("app.trial_number"));
        String otp = generateOTP(phoneNumber).toString();
        System.out.println(otp);
        String otpMessage = "Dear User, Your OTP is:" + otp + " Use this to change your password...Thank You";
        Message message = Message.creator(to, from, otpMessage).create();
        return "OTP send Successfully to Mobile Number";
    }

    private Integer generateOTP(String key) {

        Random random = new Random();
        Integer OTP = 100000 + random.nextInt(900000);
        otpCache.put(key, OTP);
        return OTP;
    }


    @Override
    public boolean loginUser(UserDto userDto)
    {
        User user = authenticationRepository.findByEmail(userDto.getEmail()).get();
        if (user != null)
        {
            String password = userDto.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password,encodedPassword);
            if (isPwdRight)
            {
                return true;
            } else
            {
                return false;
            }
        }

        return false;
    }

    @Override
    public String sendOtpForForgetPassword(String phoneNumber)
    {
        User user=authenticationRepository.findByMobileNumber(phoneNumber);
        if(user!=null)
        {
            return otpForForgetPassword(phoneNumber);
        }
        return "Mobile is not Registered";

    }



    @Override
    public String changePassword(String mobileNumber,String newPassword) {
         if(newPassword==null)return "NULL password is not accepted";

         String encodedOldPassword=authenticationRepository.getOldPassword(mobileNumber);
         if(passwordEncoder.matches(newPassword,encodedOldPassword))// if new and old password matches
         {
             return "Same as Old Password Try something new password";
         }
         else
         {
             authenticationRepository.setNewPassword(mobileNumber,passwordEncoder.encode(newPassword));
         }

         return "Password changed successfully";

    }

    @Override
    public String otpVerification(String mobileNumber,String otp)
    {

        String pto = getOPTByKey(mobileNumber);
        if(!otp.equals(pto))
        {
            return "Invalid Otp";
        }
        return "otp checked successfully";

    }

}
