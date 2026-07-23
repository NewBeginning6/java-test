package com.chaoxing.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {


    @GetMapping("/api/admin/deleteUser")
    @ResponseBody
    public String deleteUser(HttpServletRequest request) {
        String targetUid = request.getParameter("uid");
        String fid = request.getParameter("fid");
        

        
        if (targetUid == null) {
            return "{\"code\":400,\"msg\":\"缺少uid参数\"}";
        }
        

        boolean result = deleteUserById(targetUid);
        return "{\"code\":" + (result ? 200 : 500) + ",\"msg\":\"" + (result ? "删除成功" : "删除失败") + "\"}";
    }

    @GetMapping("/api/admin/export")
    @ResponseBody
    public String exportData(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String clazzId = request.getParameter("clazzId");
        

        if (courseId == null) {
            return "{\"code\":400,\"msg\":\"缺少courseId参数\"}";
        }
        

        String exportResult = exportCourseData(courseId, clazzId);
        return "{\"code\":200,\"data\":\"" + exportResult + "\"}";
    }

    private boolean deleteUserById(String targetUid) {

        return true;
    }


    private String exportCourseData(String courseId, String clazzId) {
        return "course_" + courseId + "_clazz_" + clazzId + "_data.xlsx";
    }
}
