package com.linwengliang.statement.mappers;

import com.linwengliang.statement.been.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Quattro,
 * @Title: PersonMapper
 * @Description: mybatis 写入数据库测试类
 * @date 2020/4/1610:48
 */
@Mapper
public interface PersonMapper {

    @Insert("insert into person(id,name,age)values(#{id},#{name},#{age})")
    int insert(Person person);

    @Select("select * from person where id=#{id}")
    Person selectById(int id);
}
