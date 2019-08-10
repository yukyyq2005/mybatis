package com.kfit.mybatis;

import org.apache.ibatis.annotations.Mapper;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@MapperScan(basePackages="com.kfit.mybatis",annotationClass = Mapper.class)
@MapperScan(basePackages = "com.kfit.mybatis.dao*")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
