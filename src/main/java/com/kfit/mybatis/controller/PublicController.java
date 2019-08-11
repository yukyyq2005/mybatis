package com.kfit.mybatis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：youq
 * @date ：Created in 2019/8/11 15:41
 * @modified By：
 */
@RestController
@RequestMapping("pub")
public class PublicController {
    @RequestMapping("need_login")
    public Object needLogin(){
        return "需要登录才能访问";
    }
    @RequestMapping("not_permit")
    public Object notPermis(){
        return "没有权限，拒绝访问";
    }
    @RequestMapping("index")
    public Object index(){
        return "index";
    }
}
