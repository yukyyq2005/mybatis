package com.kfit.mybatis.service.Impl;

import com.kfit.mybatis.service.IPerson;

/**
 * @author ：youq
 * @date ：Created in 2019/7/13 12:18
 * @modified By：
 */
public class Man implements IPerson {
    @Override
    public void say(String id) {
        System.out.println("man say:"+id);
    }
}
