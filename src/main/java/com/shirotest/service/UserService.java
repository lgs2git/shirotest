package com.shirotest.service;

import com.shirotest.dao.UserDao;
import com.shirotest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findByUserName(String name){
        return userDao.findByUsername(name);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }
}
