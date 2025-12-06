package com.rabbiter.ordering.service;

import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.Foods;
import com.rabbiter.ordering.mapper.FoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodsService {

    @Resource
    private FoodsMapper foodsMapper;

    /**
     * 新增管理员
     *
     * @param foods
     */
    public void add(Foods foods) {
        foodsMapper.insert(foods);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        foodsMapper.deleteById(id);
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

    public void updateById(Foods foods) {
        foodsMapper.updateById(foods);
    }

    public Foods selectById(Integer id) {
        return foodsMapper.selectById(id);
    }


    public PageInfo selectPage(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Foods> list = foodsMapper.findAll(params);
        return PageInfo.of(list);
    }

    public List<Foods> selectAll() {
        return foodsMapper.selectAll();
    }
}
