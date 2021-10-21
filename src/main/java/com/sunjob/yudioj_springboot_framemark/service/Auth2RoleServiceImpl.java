package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.Auth2RoleMapper;
import com.sunjob.yudioj_springboot_framemark.vo.Auth2Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
