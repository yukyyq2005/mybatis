package com.kfit.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：youq
 * @date ：Created in 2019/6/23 17:13
 * @modified By：
 */
@Controller
public class InitController {
    @RequestMapping("/websocket")
    public String init() {
        return "index.html";
    }
}
