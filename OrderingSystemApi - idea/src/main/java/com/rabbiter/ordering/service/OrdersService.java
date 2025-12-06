package com.rabbiter.ordering.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.rabbiter.ordering.entity.Orders;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.User;
import com.rabbiter.ordering.exception.CustomException;
import com.rabbiter.ordering.mapper.OrdersMapper;
import com.rabbiter.ordering.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增订单
     *
     * @param orders
     */
    public void add(Orders orders) {
        User user = userMapper.selectById(orders.getUserId());
        int comparisonResult = user.getAccount().compareTo(orders.getTotal());
        if (comparisonResult < 0) {
            throw new CustomException("余额不足");
        }
        // 设置唯一的订单号
        String orderNo = IdUtil.fastSimpleUUID();
        orders.setOrderNo(orderNo);
        orders.setTime(DateUtil.now());  // 当前的年月日 时分秒
        ordersMapper.insert(orders);
        //扣减用户余额
        user.setAccount(user.getAccount().subtract(orders.getTotal()));
        userMapper.updateById(user);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void updateById(Orders orders) {
        if ("是".equals(orders.getStatus())) {
            orders.setStatus("已出餐");
        }else {
            orders.setStatus("待出餐");
        }
        ordersMapper.updateById(orders);
    }

    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }


    public PageInfo selectPage(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Orders> list = ordersMapper.findAll(params);
        return PageInfo.of(list);
    }

    public List<Orders> selectAll() {
        return ordersMapper.selectAll();
    }
}
