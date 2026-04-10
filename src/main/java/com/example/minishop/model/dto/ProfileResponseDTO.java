package com.example.minishop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProfileResponseDTO {
    private String username;
    private LocalDateTime loginTime;
    private Integer visitCount;
}