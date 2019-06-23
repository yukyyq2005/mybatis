package com.kfit.mybatis.service;

import com.kfit.mybatis.dao.UserMapper;
import com.kfit.mybatis.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 21:56
 * @modified By：
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public User getUser(int id) {
        User user = userMapper.getUser(id);
        return user;
    }

    @Transactional
    public int updateUser(User record) {
        return userMapper.updateUser(record);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void modifyUser() {
        User user = userMapper.getUser(1);
        user.setAge(user.getAge() + 1);
        userMapper.updateUser(user);
    }
}
