package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.Auth2RoleMapper;
import com.sunjob.yudioj_springboot_framemark.vo.Auth2Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Auth2RoleServiceImpl implements Auth2RoleService{
    @Autowired
    Auth2RoleMapper auth2RoleMapper;
    @Override
    public List<Auth2Role> findAllAuth2Role() {
        return auth2RoleMapper.findAllAuth2Role();
    }

    @Override
    public List<Auth2Role> findAllAuth2RoleWithout() {
        return auth2RoleMapper.findAllAuth2RoleWithout();
    }

    @Override
    public Auth2Role findAuth2RoleByIdWithout(String id) {
        return auth2RoleMapper.findAuth2RoleByIdWithout(id);
    }

    @Override
    public boolean authRoleModify(Auth2Role auth2Role) {
        auth2Role.setModifyTime(new Date());
        return auth2RoleMapper.authRoleModify(auth2Role);
    }

    @Override
    public boolean freezeAuthRole(String id) {
        return auth2RoleMapper.FreezeAuthRole(id,new Date());
    }

    @Override
    public boolean addAuthRole(Auth2Role auth2Role) {
        Date date = new Date();
        auth2Role.setModifyTime(date);
        auth2Role.setCreateTime(date);
        auth2Role.setId(System.currentTimeMillis()+"");
        return auth2RoleMapper.addAuthRole(auth2Role);
    }

}
