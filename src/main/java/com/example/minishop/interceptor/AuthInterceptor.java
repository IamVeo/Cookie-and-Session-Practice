package com.example.minishop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // Kiểm tra xem username đã tồn tại trong session chưa
        if (session.getAttribute("username") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // 1. Thiết lập định dạng trả về và mã hóa tiếng Việt
            response.setContentType("text/plain; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            // 2. Viết nội dung
            response.getWriter().write("Ban phai dang nhap de truy cap chuc nang nay!");

            // 3. Quan trọng: Đẩy dữ liệu ra Body ngay lập tức
            response.getWriter().flush();

            return false;
        }

        return true; // Cho phép request đi tiếp vào Controller
    }
}