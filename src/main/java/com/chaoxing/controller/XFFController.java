package com.chaoxing.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XFFController {

    @GetMapping("/api/ip/xff")
    @ResponseBody
    public String getIpByXFF(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return "{\"code\":200,\"ip\":\"" + ip + "\"}";
    }

    @GetMapping("/api/ip/xff-login")
    @ResponseBody
    public String loginByXFF(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip) || "localhost".equals(ip)) {
            return "{\"code\":200,\"msg\":\"内网登录成功\",\"role\":\"admin\"}";
        }
        return "{\"code\":403,\"msg\":\"仅允许内网访问\"}";
    }
}
