package com.rabbiter.ordering.service;

import cn.hutool.core.util.ObjectUtil;
import com.rabbiter.ordering.common.RoleEnum;
import com.rabbiter.ordering.entity.Account;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.entity.User;
import com.rabbiter.ordering.exception.CustomException;
import com.rabbiter.ordering.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Account login(Account account) {
        String username = account.getUsername();
        User dbuser = userMapper.selectByUsername(username);
        if(dbuser == null){
            throw new CustomException("账号不存在");
        }
        if(!dbuser.getPassword().equals(account.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        return dbuser;
    }

    public void register(User user) {
        if(ObjectUtil.isEmpty(user.getUsername())){
            throw new CustomException("账号不能为空");
        }
        if(ObjectUtil.isEmpty(user.getPassword())){
            throw new CustomException("密码不能为空");
        }
        User dbuser = userMapper.selectByUsername(user.getUsername());
        if(dbuser != null){
            throw new CustomException("账号已存在");
        }
        user.setName(user.getUsername());
        userMapper.insert(user);
    }

    public void add(User user) {
        if (ObjectUtil.isEmpty(user.getUsername())) {
            throw new CustomException("账号不能为空");
        }
        User dbuser = userMapper.selectByUsername(user.getUsername());
        if (dbuser != null) {
            throw new CustomException("账号已存在");
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword("123456");//默认密码
        }
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void update(User user) {
        userMapper.updateById(user);
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public PageInfo selectPage(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<User> list = userMapper.findAll(params);
        return PageInfo.of(list);
    }
}
