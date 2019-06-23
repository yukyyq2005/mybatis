package com.kfit.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author ：youq
 * @date ：Created in 2019/6/23 16:37
 * @modified By：
 */
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {
    private Logger log = LoggerFactory.getLogger(getClass());
    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Object pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(cid+"#"+e.getMessage());
            return "error";
        }
        log.info(cid+"success");
        return "ok";
    }
}
