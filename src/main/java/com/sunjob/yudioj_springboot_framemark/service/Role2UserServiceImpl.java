package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.Role2UserMapper;
import com.sunjob.yudioj_springboot_framemark.vo.Role2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Role2UserServiceImpl implements Role2UserService{
    @Autowired
    Role2UserMapper role2UserMapper;

    @Override
    public List<Role2User> findAllRole2UserWithout() {
        return role2UserMapper.findAllRole2UserWithout();
    }

    @Override
    public boolean modifyUserRole(Role2User role2User) {
        role2User.setModifyTime(new Date());
        return role2UserMapper.modifyUserRole(role2User);
    }

    @Override
    public Role2User findRole2UserById(String id) {
        return  role2UserMapper.findRole2UserById(id);
    }

    @Override
    public List<Role2User> findAllRole2User() {
        return role2UserMapper.findAllRole2User();
    }

    @Override
    public boolean freezeRole2User(String id) {
        return role2UserMapper.freezeRole2User(id,new Date());
    }

    @Override
    public boolean addUserRole(Role2User role2User) {
        Date date = new Date();
        role2User.setModifyTime(date);
        role2User.setCreateTime(date);
        role2User.setId(System.currentTimeMillis()+"");
        return role2UserMapper.addUserRole(role2User);
    }
}
