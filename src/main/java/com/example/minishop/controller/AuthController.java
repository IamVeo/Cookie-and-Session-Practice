package com.example.minishop.controller;

import com.example.minishop.model.dto.LoginRequest;
import com.example.minishop.model.dto.MessageResponseDTO;
import com.example.minishop.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDTO> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        authService.login(loginRequest, session);
        return ResponseEntity.ok(new MessageResponseDTO("Đăng nhập thành công!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponseDTO> logout(HttpSession session) {
        authService.logout(session);
        return ResponseEntity.ok(new MessageResponseDTO("Đã đăng xuất thành công!"));
    }
}