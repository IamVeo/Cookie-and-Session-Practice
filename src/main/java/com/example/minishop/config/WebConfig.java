package com.example.minishop.config;

import com.example.minishop.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/profile", "/api/admin/**")
                .excludePathPatterns(
                        "/api",          // Thêm cái này để khớp với Postman bạn dùng
                        "/api/",         // Giữ cái này cho trình duyệt
                        "/api/login",
                        "/api/set-theme/**"
                );
    }
}