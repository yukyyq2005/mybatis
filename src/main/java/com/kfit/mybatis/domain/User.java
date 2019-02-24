package com.kfit.mybatis.domain;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 20:21
 * @modified By：
 */

import java.io.Serializable;

/**
 * @author gacl
 * users表所对应的实体类
 */
public class User implements Serializable {

    //实体类的属性和表的字段名称一一对应
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}