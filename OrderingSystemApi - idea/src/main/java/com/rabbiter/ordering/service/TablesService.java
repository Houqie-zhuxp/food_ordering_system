package com.rabbiter.ordering.service;

import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.Tables;
import com.rabbiter.ordering.exception.CustomException;
import com.rabbiter.ordering.mapper.TablesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesService {

    @Resource
    private TablesMapper tablesMapper;

    /**
     * 新增管理员
     *
     * @param tables
     */
    public void add(Tables tables) {
        tablesMapper.insert(tables);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        tablesMapper.deleteById(id);
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

    public void updateById(Tables tables) {
        if ("是".equals(tables.getFree())) {
            tables.setUserId(null);  // 清除占用的顾客信息
        }
        tablesMapper.updateById(tables);
    }

    public Tables selectById(Integer id) {
        return tablesMapper.selectById(id);
    }

    public void addOrder(Tables tables) {
        // 先查询当前的用户有没有占用餐桌
        Tables dbTables = tablesMapper.selectByUserId(tables.getUserId());
        if (dbTables != null && !dbTables.getId().equals(tables.getId())) {
            throw new CustomException("您已经预定了其他餐桌");
        }
        //设置餐桌状态占用
        tables.setFree("否");
        this.updateById(tables);
    }

    public void removeOrder(Tables tables) {
        tablesMapper.removeOrder(tables.getId());
    }

    public PageInfo selectPage(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Tables> list = tablesMapper.findAll(params);
        return PageInfo.of(list);
    }

    public Tables selectByUserId(Integer userId) {
        return tablesMapper.selectByUserId(userId);
    }

    public List<Tables> selectAll() {
        return tablesMapper.selectAll();
    }
}
