package com.example.SPE_Major_project.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class OtpService
{

    private static final Integer EXPIRE_MIN = 5;
    private static LoadingCache<String, Integer> otpCache;

    public OtpService()
    {
        super();
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }
    public static Integer generateOTP(String key)
    {
        Random random = new Random();
        int OTP = 100000 + random.nextInt(900000);
        otpCache.put(key, OTP);

        return OTP;
    }

    public static String getOPTByKey(String key)
    {
        Integer otp = otpCache.getIfPresent(key);
        if(otp==null)
            return "-99";
        else return otp.toString();
    }

    public static void clearOTPFromCache(String key) {
        otpCache.invalidate(key);
    }

    public static Integer sendOTP(String phoneNumber, String message) {
        //Third party API call
        int code=-1;
        try
        {
            String apiKey="Tf0l5vJFUNs3EqRDwmVpjkIHLbYXSBtxGicuhyPQdW9nr2e178brnxh0MUt2DmlTGoPKJLEvaAYN9SW6";
            String sendId="FSTSMS";
            message= URLEncoder.encode(message, "UTF-8");
            String language="english";
            String route="p";
            String myMessage = "Hello there, "+message+ " is your one time password (OTP) to register";
            String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sendId+"&message="+myMessage+"&language="+language+"&route="+route+"&numbers="+phoneNumber;

            URL url=new URL(myUrl);
            HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            code=con.getResponseCode();
            StringBuffer response=new StringBuffer();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true)
            {
                String line=br.readLine();
                if(line==null)
                {
                    break;
                }
                response.append(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
    public static Integer forgotOtp(String phoneNumber, String message)
    {
        int code=-1;
        try
        {
            String apiKey="Tf0l5vJFUNs3EqRDwmVpjkIHLbYXSBtxGicuhyPQdW9nr2e178brnxh0MUt2DmlTGoPKJLEvaAYN9SW6";
            String sendId="FSTSMS";
            message= URLEncoder.encode(message, "UTF-8");
            String language="english";
            String route="p";
            String myMessage = message+ " is your one time (OTP) for password change";
            String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sendId+"&message="+myMessage+"&language="+language+"&route="+route+"&numbers="+phoneNumber;

            URL url=new URL(myUrl);
            HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            code=con.getResponseCode();
            StringBuffer response=new StringBuffer();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true)
            {
                String line=br.readLine();
                if(line==null)
                {
                    break;
                }
                response.append(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        log.info("");
        return code;
    }
}
