package com.linwengliang.statement.dao.impl;

import com.linwengliang.statement.been.Person;
import com.linwengliang.statement.mappers.PersonMapper;
import com.linwengliang.statement.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Quattro,
 * @Title: PersonDaompl
 * @Description: mybatis 写入数据库测试实现类
 * @date 2020/4/1610:56
 */
@Service
public class PersonDaompl implements PersonDao {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public int insertPerson(Person person) {
        return personMapper.insert(person);
    }

    @Override
    public Person selectById(int id) {
        return personMapper.selectById(id);
    }
}
