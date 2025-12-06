package com.rabbiter.ordering.service;

import cn.hutool.core.util.ObjectUtil;
import com.rabbiter.ordering.common.RoleEnum;
import com.rabbiter.ordering.entity.Account;
import com.rabbiter.ordering.entity.Admin;
import com.rabbiter.ordering.entity.Params;
import com.rabbiter.ordering.exception.CustomException;
import com.rabbiter.ordering.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @param account
     * @return
     */
    public Account login(Account account) {
        Admin dbadmin = adminMapper.selectByUsername(account.getUsername());
        if (dbadmin == null) {
            throw new CustomException("账号不存在");
        }
        if (!dbadmin.getPassword().equals(account.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        return dbadmin;
    }

    /**
     * 新增管理员
     *
     * @param admin
     */
    public void add(Admin admin) {
        if (ObjectUtil.isEmpty(admin.getUsername())) {
            throw new CustomException("账号不能为空");
        }
        Admin dbadmin = adminMapper.selectByUsername(admin.getUsername());
        if (dbadmin != null) {
            throw new CustomException("账号已存在");
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword("123456");//默认密码
        }
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole(RoleEnum.ADMIN.name());
        adminMapper.insert(admin);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
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

    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }

    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Admin> selectAll(String name) {
        return adminMapper.selectAll(name);
    }


    public PageInfo selectPage(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Admin> list = adminMapper.findAll(params);
        return PageInfo.of(list);
    }
}
