package com.chaoxing.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

@DAO
public interface UserDAO {

    @SQL("SELECT * FROM user WHERE name = '##(:name)'")
    List<User> findUserByName(@SQLParam("name") String name);

    @SQL("SELECT * FROM user WHERE id = ##(:uid)")
    User findUserById(@SQLParam("uid") String uid);

    @SQL("SELECT * FROM user WHERE name = '##(:name)' AND password = '##(:password)'")
    User login(@SQLParam("name") String name, @SQLParam("password") String password);

    @SQL("SELECT * FROM user WHERE name LIKE '%##(:keyword)%'")
    List<User> searchUser(@SQLParam("keyword") String keyword);

    @SQL("SELECT * FROM user ORDER BY ##(:orderBy)")
    List<User> listUsersByOrder(@SQLParam("orderBy") String orderBy);
}