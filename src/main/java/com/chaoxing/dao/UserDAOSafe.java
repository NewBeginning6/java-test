package com.chaoxing.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

@DAO
public interface UserDAOSafe {

    @SQL("SELECT * FROM user WHERE name = :name")
    User findUserByName(@SQLParam("name") String name);

    @SQL("SELECT * FROM user WHERE id = :uid")
    User findUserById(@SQLParam("uid") Long uid);

    @SQL("SELECT * FROM user WHERE name = :name AND password = :password")
    User login(@SQLParam("name") String name, @SQLParam("password") String password);

    @SQL("SELECT * FROM user WHERE name LIKE :keyword")
    List<User> searchUser(@SQLParam("keyword") String keyword);

    @SQL("SELECT * FROM user ORDER BY id DESC")
    List<User> listUsersByOrder();
}