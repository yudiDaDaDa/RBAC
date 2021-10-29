package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.RoleMapper;
import com.sunjob.yudioj_springboot_framemark.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
@Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> getALlRoleWithout() {
        return roleMapper.findAllRoleWithout();
    }

    @Override
    public List<Role> getALlRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public Role getRoleById(String id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public boolean modifyRole(Role role) {
      role.setModifyTime(new Date());
      return roleMapper.modifyRole(role);
    }

    @Override
    public boolean roleFreeze(String id) {
        return roleMapper.roleFreeze(id);
    }

    @Override
    public boolean addRole(Role role) {
        Date date = new Date();
        role.setModifyTime(date);
        role.setCreateTime(date);
        role.setId(System.currentTimeMillis()+"");
        return roleMapper.addRole(role);
    }
}
