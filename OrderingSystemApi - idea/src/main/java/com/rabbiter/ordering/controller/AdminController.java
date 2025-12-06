package com.rabbiter.ordering.controller;

import com.rabbiter.ordering.common.Result;
import com.rabbiter.ordering.entity.Admin;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 新增管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
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
        adminService.deleteById(id);
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
        adminService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(String name) {
        List<Admin> list = adminService.selectAll(name);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo pageInfo = adminService.selectPage(params);
        return Result.success(pageInfo);
    }
}
