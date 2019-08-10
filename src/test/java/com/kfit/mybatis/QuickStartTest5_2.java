package com.kfit.mybatis;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 自定义realm
 * @author ：youq
 * @date ：Created in 2019/8/10 14:38
 * @modified By：
 */
public class QuickStartTest5_2 {
    private CustomRealm accountRealm = new CustomRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        //初始化数据源
//        accountRealm.addAccount("xdclass","123");
//        accountRealm.addAccount("jack","456");
        //构建环境
        defaultSecurityManager.setRealm(accountRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }
    @Test
    public void testAuthentication(){
        //当前操作主体，application  user
        Subject subject = SecurityUtils.getSubject();
        //用户输入的账号和密码
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("xdclass33","123");
        subject.login(usernamePasswordToken);

        System.out.println("认证结果："+subject.isAuthenticated()+subject.getPrincipals());

        System.out.println("是否有角色："+subject.hasRole("role"));
        System.out.println("是否有权限："+subject.isPermitted("pubd:buy:srwe"));
    }
}
