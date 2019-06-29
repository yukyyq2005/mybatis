package com.kfit.mybatis.test;

import com.kfit.mybatis.dao.UserMapper;
import com.kfit.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：youq
 * @date ：Created in 2019/6/29 23:17
 * @modified By：
 */
//@Component
public class ProducerInt {

  //  @Autowired
    UserMapper userMapper;
    private User clerk;

    public ProducerInt(User clerk){
        this.clerk = clerk;
    }
    public void run() {
        userMapper.insert(this.clerk);
        System.out.println("生产者开始生产整数了..................");
    }
}
