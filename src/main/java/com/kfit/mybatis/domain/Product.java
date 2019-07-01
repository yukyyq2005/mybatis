package com.kfit.mybatis.domain;

import java.util.Date;

/**
 * @author ：youq
 * @date ：Created in 2019/7/1 22:24
 * @modified By：
 */
public class Product {
    private  String name;
    private int price;

    public Product(String name, int price, Date date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
}
