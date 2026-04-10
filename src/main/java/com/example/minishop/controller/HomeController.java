package com.example.minishop.controller;

import com.example.minishop.model.dto.HomeResponseDTO;
import com.example.minishop.model.dto.MessageResponseDTO;
import com.example.minishop.service.AuthService;
import com.example.minishop.service.ThemeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<HomeResponseDTO> getHome(
            @CookieValue(value = "theme", defaultValue = "light") String theme,
            HttpSession session) {

        String username = (String) session.getAttribute("username");
        boolean isLoggedIn = authService.isLoggedIn(session);

        String message = isLoggedIn ? "Xin chào, " + username : "Bạn chưa đăng nhập";

        HomeResponseDTO response = HomeResponseDTO.builder()
                .title("Mini Profile App")
                .message(message)
                .theme(theme)
                .isLoggedIn(isLoggedIn)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/set-theme/{theme}")
    public ResponseEntity<MessageResponseDTO> setTheme(
            @PathVariable String theme,
            HttpServletResponse response) {

        if (!themeService.isValidTheme(theme)) {
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Theme không hợp lệ! (Chỉ dùng light hoặc dark)"));
        }

        Cookie cookie = new Cookie("theme", theme);
        cookie.setMaxAge(600);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(new MessageResponseDTO("Đã lưu theme: " + theme));
    }
}