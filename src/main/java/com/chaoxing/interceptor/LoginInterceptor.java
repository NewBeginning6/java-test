package com.chaoxing.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.contains("/user/") || path.contains("/admin/")) {
            Cookie[] cookies = request.getCookies();
            String uid = null;
            String fid = null;
            String vc3 = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("UID".equals(cookie.getName())) {
                        uid = cookie.getValue();
                    }
                    if ("FID".equals(cookie.getName())) {
                        fid = cookie.getValue();
                    }
                    if ("VC3".equals(cookie.getName())) {
                        vc3 = cookie.getValue();
                    }
                }
            }

            if (uid == null || uid.isEmpty() || fid == null || fid.isEmpty()) {
                response.setStatus(401);
                response.getWriter().write("{\"code\":401,\"msg\":\"未登录\"}");
                return false;
            }


            request.setAttribute("currentUid", uid);
            request.setAttribute("currentFid", fid);
        }

        return true;
    }
}