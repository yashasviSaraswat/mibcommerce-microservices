package com.mibcommerce.user.controller;
import com.mibcommerce.common.dto.UserDto;

import com.mibcommerce.common.dto.ApiResponse;
import com.mibcommerce.common.dto.UserDto;
import com.mibcommerce.security.UserPrincipal;
import com.mibcommerce.user.dto.UpdateProfileRequest;
import com.mibcommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMyProfile(
            @AuthenticationPrincipal UserPrincipal principal) {
        ApiResponse<Map<String, Object>> response = userService.getUserProfile(principal.getUserId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<String>> updateMyProfile(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody UpdateProfileRequest request) {
        ApiResponse<String> response = userService.updateProfile(principal.getUserId(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        ApiResponse<UserDto> response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
}