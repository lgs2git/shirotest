package com.shirotest.dao;

import com.shirotest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
