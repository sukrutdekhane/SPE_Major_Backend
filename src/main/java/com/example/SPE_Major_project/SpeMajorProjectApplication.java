package com.example.SPE_Major_project;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
public class SpeMajorProjectApplication {

	private final Environment env;

	@PostConstruct
	public void initTwilio()
	{
		Twilio.init(env.getProperty("app.account_sid"),env.getProperty("app.auth_token"));
	}
	public static void main(String[] args) {
		SpringApplication.run(SpeMajorProjectApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**").allowedOrigins("*");
			}
		};

	}
}
