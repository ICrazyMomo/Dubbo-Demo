package com.linwengliang.statement.controller;

import com.linwengliang.statement.been.Person;
import com.linwengliang.statement.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Quattro,
 * @Title: PersonController
 * @Description: mybatis 写入数据库测试controller
 * @date 2020/4/1610:58
 */
@RestController
public class PersonController {

    @Autowired
    private PersonDao personService;

    @RequestMapping("/add")
    public String addStudent(){
        Person p = new Person();
        p.setId(1);
        p.setName("lwl");
        p.setAge(20);
        int result = personService.insertPerson(p);
        System.out.println("插入的结果是"+result);
        return result+"";
    }

    @RequestMapping("/query")
    public String queryPerson(){
       Person p = personService.selectById(1);
       return p.getName();
    }

}
