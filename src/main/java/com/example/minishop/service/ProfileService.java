package com.example.minishop.service;

import com.example.minishop.model.dto.ProfileResponseDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProfileService {

    public ProfileResponseDTO getProfileAndUpdateCount(HttpSession session) {
        String username = (String) session.getAttribute("username");
        LocalDateTime loginTime = (LocalDateTime) session.getAttribute("loginTime");

        Integer count = (Integer) session.getAttribute("visitCount");
        if (count == null) count = 0;
        count++;
        session.setAttribute("visitCount", count);

        return new ProfileResponseDTO(username, loginTime, count);
    }
}