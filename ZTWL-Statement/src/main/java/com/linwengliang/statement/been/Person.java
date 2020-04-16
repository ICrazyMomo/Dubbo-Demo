package com.linwengliang.statement.been;

import java.io.Serializable;

/**
 * @author Quattro,
 * @Title: Person
 * @Description: mybatis 写入数据库测试类
 * @date 2020/4/1610:40
 */
public class Person implements Serializable {
    private int id;
    private String name;
    private int age;

    public Person(){

    }
    public Person(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

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
}
