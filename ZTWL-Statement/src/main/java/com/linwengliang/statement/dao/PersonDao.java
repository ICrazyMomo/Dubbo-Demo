package com.linwengliang.statement.dao;

import com.linwengliang.statement.been.Person;

/**
 * @author Quattro,
 * @Title: PersonDao
 * @Description: mybatis 写入数据库测试类
 * @date 2020/4/1610:52
 */
public interface PersonDao {

    /**
     * 增加一个person对象
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Person selectById(int id);
}
