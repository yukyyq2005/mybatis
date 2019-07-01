package com.kfit.mybatis.domain;

/**
 * @author ：youq
 * @date ：Created in 2019/7/1 23:22
 * @modified By：
 */
public class UserInfo {
    private String name;
    private String content;

    public UserInfo(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
