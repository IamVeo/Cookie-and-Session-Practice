package com.example.minishop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeResponseDTO {
    private String title;
    private String message;
    private String theme;
    private boolean isLoggedIn;
}