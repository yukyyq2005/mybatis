package com.kfit.mybatis.other;

import com.kfit.mybatis.service.UserService;
import com.kfit.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 21:53
 * @modified By：
 */
public class DisplayMessage extends Thread {
    @Resource
    private UserService userService;
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    public void setObject(UserService user) {
        userService = user;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            userService.modifyUser();
        }
    }
}
