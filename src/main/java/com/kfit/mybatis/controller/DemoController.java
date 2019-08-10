package com.kfit.mybatis.controller;

import com.kfit.mybatis.OperationLogSaver;
import com.kfit.mybatis.service.UserService;
import com.kfit.mybatis.domain.User;
import com.kfit.mybatis.other.DisplayMessage;
import com.kfit.mybatis.test.WebSocketTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 20:40
 * @modified By：
 */
@RestController
public class DemoController {

    @Resource
    private UserService userService;
    @Autowired
    private OperationLogSaver operationLogSaver;

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
        // 保存操作记录
       // List<TSysUpdateLog> records = new ArrayList<>();
// 异步写日志
        //测试
        /*User user1 = new User();
        user1.setAge(234);
        user1.setName("马云1");
        User user2 = new User();
        user2.setId(16);
        user2.setAge(234);
        user2.setName("马云2");
        ArrayList<User> arrayList = new ArrayList<User>();
        arrayList.add(user1);
        arrayList.add(user2);
        operationLogSaver.putRecord(arrayList);*/
        Object user = userService.getUser(1);
        return user;
    }
}

















