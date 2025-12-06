package com.rabbiter.ordering.mapper;

import com.rabbiter.ordering.entity.Orders;
import com.rabbiter.ordering.entity.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper {

    // @Select("select * from orders where name = #{username}")
    // User selectByUsername(String username);

    void insert(Orders orders);

    void deleteById(Integer id);

    void updateById(Orders orders);

    List<Orders> findAll(@Param("params")Params params);

    @Select("select * from orders where id =#{id}")
    Orders selectById(Integer id);

    @Select("select * from orders order by id desc")
    List<Orders> selectAll();
}
