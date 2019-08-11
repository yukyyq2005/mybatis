package com.kfit.mybatis.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：youq
 * @date ：Created in 2019/8/10 22:17
 * @modified By：
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("ShiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //需要登录的接口，如果访问某个接口，需要登录却没有登录，则调用此接口（如果不是
        // 前后端分离，则跳转页面）
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        //登录成功，跳转url，如果前后端分离，则没这个调用
        shiroFilterFactoryBean.setSuccessUrl("/");
        //登录了但是没有权限，类似403
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");
        //拦截器路径
        // 坑1 必须使用有序的linkedhashmap
        Map filter = new LinkedHashMap<>();
        //退出过滤器
        filter.put("/logout","logout");
//        //匿名可以访问，游客模式
        filter.put("/pub/**","anon");
//        //登录用户才可以访问
        filter.put("/authc/**","authc");
//        //管理员角色才可以访问
        filter.put("/admin/**","roles[admin]");
//        //有编辑权限的才可以访问
        filter.put("/video/update","perms[video_update]");
//        //全局url authc ：url定义必须通过认证才可以访问
//        //anon: url可以匿名访问
        filter.put("/**","authc");
        //坑2 过滤器是顺序执行，从上到下，一般/** 放最下面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离，就不用setSessionManager
        securityManager.setSessionManager(sessionManager());
        //这里放最后，不然有的版本不生效
        securityManager.setRealm(customRealm());
        return securityManager;
    }
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        //customRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置加密
        return customRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法，设置md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列2次，相当于md5(md5(xxx))
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //超时时间，默认30分钟，单位是毫秒
//        customSessionManager.setGlobalSessionTimeout(10*1000);
        return customSessionManager;
    }
}











