package com.rabbiter.ordering.mapper;

import com.rabbiter.ordering.entity.Admin;
import com.rabbiter.ordering.entity.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);

    void insert(Admin admin);

    void deleteById(Integer id);

    void updateById(Admin admin);

    @Select("select * from admin where id =#{id}")
    Admin selectById(Integer id);

    //@Select("select * from admin Where name like concat('%',#{name},'%')  order by id desc")
    List<Admin> selectAll(String name);

    List<Admin> findAll(@Param("params")Params params);
}
