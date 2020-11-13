package com.shirotest.testShiro;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realm，需要继承AuthorizingRealm父类
 *     重写父类中的两个方法
 *         doGetAuthorizationInfo     ：授权
 *         doGetAuthenticationInfo     ：认证
 */
public class PermissionRealm extends AuthorizingRealm {

    public void setName(String name){
        super.setName("permissionRealm");
    }

    /**
     * 授权：授权的主要目的就是查询数据库获取用户的所有角色和权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权方法-------");

        // 1.从principals获取已认证用户的信息
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 2.从数据库查询用户
        // 3.查询用户角色或权限数据
        List<String> perms = new ArrayList<>();
        perms.add("user:save");
        perms.add("user:update");
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");
        // 4.构造权限数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 6.将查询的角色数据保存到simpleAuthorizationInfo
        info.addRoles(roles);
        // 5.将查询的权限数据保存到simpleAuthorizationInfo
        info.addStringPermissions(perms);

        return info;
    }

    /**
     * 认证：认证的主要目的，比较用户输入的用户名密码是否和数据库中的一致
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证方法-------");

        //1.获取登录的upToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //2.获取输入的用户名密码
        String username = upToken.getUsername();
        char[] pw = upToken.getPassword();
        String password = new String(pw);
        //3.根据用户名查询数据库
        //4.验证密码是否一致
        if ("123456".equals(password)){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
            return info;
        }else {
            throw new RuntimeException("用户名或密码错误！");
        }
    }
}
