package com.lgs.shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroTest03 {

    private SecurityManager securityManager;

    @Before
    public void init(){
        //1.加载ini配置文件创建SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-3.ini");
        //2.获取securityManager
        SecurityManager securityManager = factory.getInstance();
        //3.将securityManager绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testLogin(){

        //4.创建主体(此时的主体还为经过认证)
        Subject subject = SecurityUtils.getSubject();
        //5.构造主体登录的凭证（即用户名/密码）
        //第一个参数：登录用户名，第二个参数：登录密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
        //6.主体登录  执行realm中认证方法
        subject.login(token);


        //7.用户认证成功之后才可以完成授权工作   执行realm中授权方法
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:update"));
    }
}
