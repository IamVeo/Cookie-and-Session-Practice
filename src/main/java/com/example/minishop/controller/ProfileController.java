package com.example.minishop.controller;

import com.example.minishop.model.dto.ProfileResponseDTO;
import com.example.minishop.service.AuthService;
import com.example.minishop.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpSession session) {
        if (!authService.isLoggedIn(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Vui lòng đăng nhập để truy cập trang này!");
        }

        ProfileResponseDTO profile = profileService.getProfileAndUpdateCount(session);
        return ResponseEntity.ok(profile);
    }
}