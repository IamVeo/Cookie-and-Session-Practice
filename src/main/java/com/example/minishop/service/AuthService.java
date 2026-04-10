package com.example.minishop.service;

import com.example.minishop.model.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {

    public void login(LoginRequest loginRequest, HttpSession session) {
        session.setAttribute("username", loginRequest.getUsername());
        session.setAttribute("loginTime", LocalDateTime.now());
        session.setAttribute("visitCount", 0);
    }

    public void logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session
    }

    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("username") != null;
    }
}