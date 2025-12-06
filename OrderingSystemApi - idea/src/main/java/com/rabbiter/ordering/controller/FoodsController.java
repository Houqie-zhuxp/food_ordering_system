package com.rabbiter.ordering.controller;

import com.rabbiter.ordering.common.Result;
import com.rabbiter.ordering.entity.Foods;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.service.FoodsService;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodsController {

    @Resource
    FoodsService foodsService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Foods foods) {
        foodsService.add(foods);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        foodsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        foodsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Foods foods) {
        foodsService.updateById(foods);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Foods foods = foodsService.selectById(id);
        return Result.success(foods);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Foods> list = foodsService.selectAll();
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Foods> pageInfo = foodsService.selectPage(params);
        return Result.success(pageInfo);
    }

}
