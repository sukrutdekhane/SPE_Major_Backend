package com.example.SPE_Major_project.Service.ServiceImpl;



import com.example.SPE_Major_project.Dto.AuthenticationResponse;
import com.example.SPE_Major_project.Configuration.JwtService;
import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.Role;
import com.example.SPE_Major_project.Entity.Token;
import com.example.SPE_Major_project.Entity.TokenType;
import com.example.SPE_Major_project.Entity.User;


import com.example.SPE_Major_project.Repository.AuthenticationRepository;
import com.example.SPE_Major_project.Repository.TokenRepository;
import com.example.SPE_Major_project.Service.AuthenticationService;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import static com.example.SPE_Major_project.Service.OtpService.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    private static final Integer EXPIRE_MIN = 5;

    private static LoadingCache<String, Integer> otpCache;


    public int register(User request,String otp)
    {
        User existingUser=authenticationRepository.findByMobileNumber(request.getMobileNumber());
        if(existingUser!=null)
        {
            log.info("User already exist please login");
            return 2;
        }
        String pto = getOPTByKey(request.getMobileNumber());

        if(!otp.equals(pto))
        {
            log.info("OTP miss-match");
            return 0;
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = authenticationRepository.save(user);
        var jwtToken=jwtService.generateToken(user,Role.USER);
        log.info("User Registered");
        return 1;
    }


    @Override
    public AuthenticationResponse loginUser(UserDto userDto)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );
        var user = authenticationRepository.findByEmail(userDto.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user,user.getRole());
        revokeAllUserTokens(user);
        savedUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    private void revokeAllUserTokens(User user){
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if(validUserToken.isEmpty())
            return;
        validUserToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);

    }

    private void savedUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }







    public boolean otpForForgetPassword(String phoneNumber)
    {
        Integer otp = generateOTP(phoneNumber);
        System.out.println(otp);
        Integer re = forgotOtp(phoneNumber, otp.toString());
        log.error("Send otp to registered mobile number");
        return true;
    }






    @Override
    public boolean sendOtpForForgetPassword(String phoneNumber)
    {
        User user=authenticationRepository.findByMobileNumber(phoneNumber);
        if(user!=null)
        {
            return otpForForgetPassword(phoneNumber);
        }
        log.error("User not found ");
        return false;

    }

    @Override
    public boolean otpVerification(String phoneNumber, String otp) {

        String pto = getOPTByKey(phoneNumber);
        if(!otp.equals(pto))
        {
            log.error("OTP miss-match");
            return false;
        }
        log.error("OTP verified successfully");
        return true;
    }


    @Override
    public boolean changePassword(String mobileNumber,String newPassword) {
         if(newPassword==null)return false;

         String encodedOldPassword=authenticationRepository.getOldPassword(mobileNumber);
         if(passwordEncoder.matches(newPassword,encodedOldPassword))// if new and old password matches
         {
             log.error("New password and old password are same");
             return false;
         }
         else
         {
             authenticationRepository.setNewPassword(mobileNumber,passwordEncoder.encode(newPassword));
             log.error("New password set successfully");
         }

         return true;

    }


}
