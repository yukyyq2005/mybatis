package com.kfit.mybatis;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 * @author ：youq
 * @date ：Created in 2019/8/10 14:32
 * @modified By：
 */
//认证 (authentication) 和授权 (authorization)
public class CustomRealm extends AuthorizingRealm {
    //用户登录的时候调用
    //重写认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token获取身份信息，token代表用户输入的信息
        String name = (String) token.getPrincipal();
        String pwd = getPwdByUserNameFromDB(name);
        System.out.println("认证 doGetAuthenticationInfo "+name+" 密码 "+pwd);
        if (pwd==null || "".equals(pwd)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(name,pwd,this.getName());
        return simpleAuthenticationInfo;
    }
    //用户权限校验的时候调用
    //重写授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权 doGetAuthorizationInfo "+principalCollection.getPrimaryPrincipal());
        String name = (String) principalCollection.getPrimaryPrincipal();
        Set<String> permissions = getPermissionsByNameFromDB(name);
        Set<String> roles = getRoleByNameFromDB(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
    private String getPwdByUserNameFromDB(String name){
        return "123";
    }
    private Set<String> getPermissionsByNameFromDB(String name){
        Set<String> set = new HashSet<>();
        set.add("video:find");
        set.add("video:buy");
        set.add("pub:*");
        return set;
    }
    private Set<String> getRoleByNameFromDB(String name){
        Set<String> set = new HashSet<>();
        set.add("role");
        set.add("admin");
        return set;
    }
}
