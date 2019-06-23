package com.kfit.mybatis.controller;

import com.kfit.mybatis.service.UserService;
import com.kfit.mybatis.domain.User;
import com.kfit.mybatis.other.DisplayMessage;
import com.kfit.mybatis.test.WebSocketTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 20:40
 * @modified By：
 */
@RestController
public class DemoController {

    @Resource
    private UserService userService;

    @GetMapping("/socket")
    public Object testh(@RequestParam("num") Integer num){
        int begin = 1000;
        for (int i = begin; i< num+begin; i++) {
            WebSocketTest wSocketTest = new WebSocketTest(String.valueOf(i));
            if (!wSocketTest.start()) {
                System.out.println("测试结束！");
                break;
            }
        }
        return "ok";
    }

    @GetMapping("/test")
    public Object test() {
//        for (int i = 0; i < 10; i++) {
//            DisplayMessage thread1 = new DisplayMessage("线程1");
//            thread1.setObject(userService);
//            thread1.start();
//        }
//        return "ok";

//        User user = new User();
//        user.setId(1);
//        user.setName("你");
//        user.setAge(34);
//        userMapper.getUser(1);
         User user = userService.getUser(1);
        return user;
    }
}

















