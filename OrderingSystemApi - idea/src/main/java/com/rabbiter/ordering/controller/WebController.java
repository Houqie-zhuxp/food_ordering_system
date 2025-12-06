package com.rabbiter.ordering.controller;

import com.rabbiter.ordering.common.Result;
import com.rabbiter.ordering.common.RoleEnum;
import com.rabbiter.ordering.entity.Account;
import com.rabbiter.ordering.entity.User;
import com.rabbiter.ordering.service.AdminService;
import com.rabbiter.ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Account account){
        if(RoleEnum.ADMIN.name().equals(account.getRole())){
            account = adminService.login(account);
        }else if(RoleEnum.USER.name().equals(account.getRole())){
            account = userService.login(account);
        }else{
            return Result.error("您的参数错误");
        }
        return Result.success(account);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(RoleEnum.USER.name().equals(user.getRole())){
            userService.register(user);
        }else{
            return Result.error("您的参数错误");
        }
        return Result.success();
    }
}
