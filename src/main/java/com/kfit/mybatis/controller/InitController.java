package com.kfit.mybatis.controller;

import com.kfit.mybatis.domain.Product;
import com.kfit.mybatis.domain.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/hello")
    public String index(Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Product product = new Product("花生油", 129, sdf.parse("2018-02-18"));
        model.addAttribute("product", product);
        return "daily";
    }
    @RequestMapping(value = { "/addProduct" }, method = RequestMethod.GET)
    public String showaddProduct(Model model)throws ParseException {
        //List<Product> saleTypes = new ArrayList<Product>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        UserInfo userInfo = new UserInfo("","");
        model.addAttribute("userInfo", userInfo);
        return "addProduct";
    }
    @RequestMapping(value = { "/saveProduct" }, method = RequestMethod.POST)
    public String saveprodcut(Model model, String name) throws ParseException {
        //List productList = DAO.loadAllProducts();
        //model.addAttribute("productList", productList);
        List productList = InitController.loadAllProducts();
        model.addAttribute("productList", productList);
        return "springel";
    }
    @ResponseBody
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String add(@ModelAttribute UserInfo user){
        String username = user.getName();
        String password = user.getContent();
        return username+"__"+password;
    }
    public static List<Product> loadAllProducts() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Product> products = new ArrayList<Product>();
        try {
            products.add(new Product("花生油", Integer.valueOf(125), sdf.parse("2018-02-18")));
            products.add(new Product("苏打饼干", Integer.valueOf(15), sdf.parse("208-02-15")));
            products.add(new Product("拿铁", Integer.valueOf(45), sdf.parse("2019-02-20")));
            products.add(new Product("调和油", Integer.valueOf(20), sdf.parse("2019-02-21")));
            products.add(new Product("大豆油", Integer.valueOf(49), sdf.parse("2019-02-15")));
            products.add(new Product("玉米汁", Integer.valueOf(80), sdf.parse("2019-02-17")));
        } catch (ParseException ex) {
            throw new RuntimeException("Invalid date");
        }
        return products;
    }
}
