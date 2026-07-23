package com.chaoxing.controller;

import com.chaoxing.dao.User;
import com.chaoxing.dao.UserDAO;
import com.chaoxing.dao.UserDAOSafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/jade")
public class JadeController {

    @Autowired(required = false)
    private UserDAO userDAO;

    @Autowired(required = false)
    private UserDAOSafe userDAOSafe;

    @GetMapping("/vuln/findByName")
    @ResponseBody
    public String vulnFindByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少name参数\"}";
        }
        try {
            User user = userDAO.findUserByName(name);
            return "{\"code\":200,\"data\":" + user + ",\"note\":\"使用 ##(:name) 拼接，存在SQL注入\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/vuln/findById")
    @ResponseBody
    public String vulnFindById(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        if (uid == null || uid.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少uid参数\"}";
        }
        try {
            User user = userDAO.findUserById(uid);
            return "{\"code\":200,\"data\":" + user + ",\"note\":\"使用 ##(:uid) 拼接，存在SQL注入\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/vuln/login")
    @ResponseBody
    public String vulnLogin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (name == null || name.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少name参数\"}";
        }
        try {
            User user = userDAO.login(name, password);
            return "{\"code\":200,\"data\":" + user + ",\"note\":\"使用 ##(:name) 和 ##(:password) 拼接，存在SQL注入\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/vuln/search")
    @ResponseBody
    public String vulnSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword == null || keyword.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少keyword参数\"}";
        }
        try {
            List<User> users = userDAO.searchUser(keyword);
            return "{\"code\":200,\"data\":" + users + ",\"note\":\"使用 ##(:keyword) 拼接，存在SQL注入\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/vuln/orderBy")
    @ResponseBody
    public String vulnOrderBy(HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        if (orderBy == null || orderBy.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少orderBy参数\"}";
        }
        try {
            List<User> users = userDAO.listUsersByOrder(orderBy);
            return "{\"code\":200,\"data\":" + users + ",\"note\":\"使用 ##(:orderBy) 拼接，存在SQL注入\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/safe/findByName")
    @ResponseBody
    public String safeFindByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少name参数\"}";
        }
        try {
            User user = userDAOSafe.findUserByName(name);
            return "{\"code\":200,\"data\":" + user + ",\"note\":\"使用 :name 参数绑定，安全\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/safe/login")
    @ResponseBody
    public String safeLogin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (name == null || name.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少name参数\"}";
        }
        try {
            User user = userDAOSafe.login(name, password);
            return "{\"code\":200,\"data\":" + user + ",\"note\":\"使用 :name 和 :password 参数绑定，安全\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/safe/search")
    @ResponseBody
    public String safeSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword == null || keyword.isEmpty()) {
            return "{\"code\":400,\"msg\":\"缺少keyword参数\"}";
        }
        try {
            List<User> users = userDAOSafe.searchUser("%" + keyword + "%");
            return "{\"code\":200,\"data\":" + users + ",\"note\":\"使用 :keyword 参数绑定，LIKE通配符在参数值中拼接，安全\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/safe/orderBy")
    @ResponseBody
    public String safeOrderBy(HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "name", "email"));
        if (orderBy == null || !allowedColumns.contains(orderBy)) {
            return "{\"code\":400,\"msg\":\"非法的排序字段，仅允许: " + allowedColumns + "\"}";
        }
        try {
            List<User> users = userDAOSafe.listUsersByOrder();
            return "{\"code\":200,\"data\":" + users + ",\"note\":\"使用白名单校验排序字段，安全\"}";
        } catch (Exception e) {
            return "{\"code\":500,\"msg\":\"" + e.getMessage() + "\"}";
        }
    }
}