package com.lgs.shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroTest02 {

    private SecurityManager securityManager;

    @Before
    public void init(){
        //1.加载ini配置文件创建SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-2.ini");
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
        UsernamePasswordToken token = new UsernamePasswordToken("lisi","123456");
        //6.主体登录
        subject.login(token);
        //7.验证是否登录成功
        //System.out.println("用户是否登录成功："+subject.isAuthenticated());
        //8.登录成功获取数据
        //getPrincipal 获取登录成功的安全数据
        //System.out.println("获取数据："+subject.getPrincipal());

        //7.用户认证成功之后才可以完成授权工作
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user.delete"));
    }
}
