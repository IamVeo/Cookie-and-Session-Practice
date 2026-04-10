package com.example.minishop.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ThemeService {
    private final List<String> VALID_THEMES = Arrays.asList("light", "dark");

    public boolean isValidTheme(String theme) {
        return theme != null && VALID_THEMES.contains(theme.toLowerCase());
    }

    public String getThemeFromCookie(String themeCookie) {
        return (themeCookie != null) ? themeCookie : "light";
    }
}