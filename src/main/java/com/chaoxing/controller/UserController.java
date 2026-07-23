package com.chaoxing.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller
public class UserController {


    @GetMapping("/api/user/info")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) {
        String uid = request.getParameter("UID");
        String fid = request.getParameter("FID");
        

        if (uid == null || uid.isEmpty()) {
            return "缺少UID参数";
        }
        

        String userInfo = queryUserInfo(uid, fid);
        return "{\"code\":200,\"data\":" + userInfo + "}";
    }


    @GetMapping("/api/user/update")
    @ResponseBody
    public String updateUser(HttpServletRequest request) {
        // 危险：直接从请求参数获取目标用户ID
        String targetUid = request.getParameter("targetUid");
        String userName = request.getParameter("userName");

        
        if (targetUid == null) {
            return "缺少targetUid参数";
        }
        
        // 模拟更新用户信息
        boolean result = updateUserInfo(targetUid, userName);
        return "{\"code\":" + (result ? 200 : 500) + ",\"msg\":\"" + (result ? "成功" : "失败") + "\"}";
    }


    @GetMapping("/api/user/list")
    @ResponseBody
    public String getUserList(HttpServletRequest request) {
        String personid = request.getParameter("personid");

        
        if (personid == null) {
            personid = "default";
        }
        

        String userList = queryUserList(personid);
        return "{\"code\":200,\"data\":" + userList + "}";
    }


    private String queryUserInfo(String uid, String fid) {

        return "{\"uid\":\"" + uid + "\",\"fid\":\"" + fid + "\",\"name\":\"用户\"}";
    }


    private boolean updateUserInfo(String targetUid, String userName) {
        // 模拟更新操作
        return true;
    }


    private String queryUserList(String personid) {
        // 模拟查询
        return "[{\"personid\":\"" + personid + "\",\"name\":\"用户列表\"}]";
    }
}
