package com.mibcommerce.user.service;

import com.mibcommerce.common.dto.ApiResponse;
import com.mibcommerce.common.dto.UserDto;
import com.mibcommerce.common.exception.ResourceNotFoundException;
import com.mibcommerce.user.dto.UpdateProfileRequest;
import com.mibcommerce.user.model.User;
import com.mibcommerce.user.model.UserProfile;
import com.mibcommerce.user.repository.UserProfileRepository;
import com.mibcommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;

    public ApiResponse<Map<String, Object>> getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());
        response.put("name", user.getName());
        response.put("role", user.getRole());
        response.put("createdAt", user.getCreatedAt());

        // Get profile if exists
        profileRepository.findByUserId(userId).ifPresent(profile -> {
            response.put("phone", profile.getPhone());
            response.put("address", profile.getAddress());
            response.put("city", profile.getCity());
            response.put("state", profile.getState());
            response.put("pincode", profile.getPincode());
        });

        return ApiResponse.success(response);
    }

    @Transactional
    public ApiResponse<String> updateProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update user name if provided
        if (request.getName() != null) {
            user.setName(request.getName());
            userRepository.save(user);
        }

        // Update or create profile
        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserProfile newProfile = new UserProfile();
                    newProfile.setUser(user);
                    return newProfile;
                });

        if (request.getPhone() != null) profile.setPhone(request.getPhone());
        if (request.getAddress() != null) profile.setAddress(request.getAddress());
        if (request.getCity() != null) profile.setCity(request.getCity());
        if (request.getState() != null) profile.setState(request.getState());
        if (request.getPincode() != null) profile.setPincode(request.getPincode());

        profileRepository.save(profile);

        return ApiResponse.success("Profile updated successfully");
    }

    public ApiResponse<UserDto> getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getName(), user.getRole());
        return ApiResponse.success(userDto);
    }
}