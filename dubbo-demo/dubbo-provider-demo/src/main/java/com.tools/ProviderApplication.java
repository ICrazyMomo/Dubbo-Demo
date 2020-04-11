package com.tools;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderApplication {
    public static void main(String[] args) {
//        classpath*:spring/applicationContext.xml
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        System.out.println("启动成功！");

        try {
            System.in.read();//让此程序一直跑，表示一直提供服务
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}