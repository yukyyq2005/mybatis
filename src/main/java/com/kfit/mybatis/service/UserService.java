package com.kfit.mybatis.service;

import com.kfit.mybatis.dao.RoleMapper;
import com.kfit.mybatis.domain.Role;
import com.kfit.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 21:56
 * @modified By：
 */
@Service
public class UserService {
    //@Resource
    //private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public Object getUser(int id) {
        User user = null;
        List list = roleMapper.selectAll();
        System.out.println("role list ："+list);
        return list;
    }

    @Transactional
    public int updateUser(User record) {
        //return userMapper.updateUser(record);
        return 1;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void modifyUser() {
//        User user = userMapper.getUser(1);
//        user.setAge(user.getAge() + 1);
//        userMapper.updateUser(user);
    }
}
