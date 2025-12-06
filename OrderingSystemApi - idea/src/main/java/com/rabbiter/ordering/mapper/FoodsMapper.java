package com.rabbiter.ordering.mapper;

import com.rabbiter.ordering.entity.Foods;
import com.rabbiter.ordering.entity.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodsMapper {

    // @Select("select * from foods where name = #{username}")
    // User selectByUsername(String username);

    void insert(Foods foods);

    void deleteById(Integer id);

    void updateById(Foods foods);

    List<Foods> findAll(@Param("params")Params params);

    @Select("select * from foods where id =#{id}")
    Foods selectById(Integer id);

    @Select("select * from foods order by id desc")
    List<Foods> selectAll();
}
