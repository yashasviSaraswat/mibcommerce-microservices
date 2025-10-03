package com.mibcommerce.user.service;

import com.mibcommerce.common.dto.ApiResponse;
import com.mibcommerce.common.dto.UserDto;
import com.mibcommerce.common.exception.BadRequestException;
import com.mibcommerce.common.exception.UnauthorizedException;
import com.mibcommerce.common.security.JwtUtil;
import com.mibcommerce.user.dto.AuthResponse;
import com.mibcommerce.user.dto.LoginRequest;
import com.mibcommerce.user.dto.RegisterRequest;
import com.mibcommerce.user.model.User;
import com.mibcommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ApiResponse<AuthResponse> register(RegisterRequest request) {
        // Validate input
        if (request.getEmail() == null || request.getPassword() == null || request.getName() == null) {
            throw new BadRequestException("All fields are required");
        }

        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        // Validate password
        if (request.getPassword().length() < 8) {
            throw new BadRequestException("Password must be at least 8 characters");
        }

        // Create user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("CUSTOMER");

        user = userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole());

        // Create response
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getName(), user.getRole());
        AuthResponse authResponse = new AuthResponse(userDto, token);

        return ApiResponse.success("Registration successful", authResponse);
    }

    public ApiResponse<AuthResponse> login(LoginRequest request) {
        // Validate input
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new BadRequestException("Email and password are required");
        }

        // Find user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole());

        // Create response
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getName(), user.getRole());
        AuthResponse authResponse = new AuthResponse(userDto, token);

        return ApiResponse.success("Login successful", authResponse);
    }
}