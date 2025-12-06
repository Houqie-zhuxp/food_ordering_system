package com.rabbiter.ordering.controller;

import com.rabbiter.ordering.common.Result;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.User;
import com.rabbiter.ordering.service.UserService;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 新增管理员
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 根据ID删除管理员
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@PathVariable List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo pageInfo = userService.selectPage(params);
        return Result.success(pageInfo);
    }
}
