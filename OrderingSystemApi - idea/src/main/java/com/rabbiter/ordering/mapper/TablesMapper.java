package com.rabbiter.ordering.mapper;

import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.Tables;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TablesMapper {

    void insert(Tables tables);

    void deleteById(Integer id);

    void updateById(Tables tables);

    @Select("select * from tables where id =#{id}")
    Tables selectById(Integer id);

    @Update("update tables set user_id = null, free = '是' where id = #{id}")
    void removeOrder(Integer id);

    List<Tables> findAll(@Param("params")Params params);

    @Select("select * from tables where user_id = #{userId}" )
    Tables selectByUserId(Integer userId);

    @Select("select * from tables order by id desc")
    List<Tables> selectAll();
}
