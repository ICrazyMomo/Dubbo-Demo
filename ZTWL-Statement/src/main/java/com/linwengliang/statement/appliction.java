package com.linwengliang.statement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Quattro,
 * @Title: appliction
 * @Description: TODO
 * @date 2020/4/1611:02
 */
@SpringBootApplication
@MapperScan("com.fdd.mybatis.mapper")
public class appliction {
    public static void main(String[] args) {
        SpringApplication.run(appliction.class, args);
    }
}
