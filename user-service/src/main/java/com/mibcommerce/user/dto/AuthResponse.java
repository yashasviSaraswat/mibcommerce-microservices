package com.mibcommerce.user.dto;


import com.mibcommerce.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private UserDto user;
    private String token;
}