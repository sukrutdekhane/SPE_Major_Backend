package com.example.SPE_Major_project.Dto;

import com.example.SPE_Major_project.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsAndOtpDto
{
    private User user;
    private String otp;
}
