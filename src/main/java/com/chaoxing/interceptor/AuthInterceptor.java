package com.chaoxing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.contains("/admin/")) {
                response.setStatus(403);
                response.getWriter().write("{\"code\":403,\"msg\":\"无权限访问\"}");
                return false;
            
        }

        return true;
    }
}
