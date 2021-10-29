package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getALlRoleWithout();
    List<Role> getALlRole();
    Role getRoleById(String id);

    boolean modifyRole(Role role);
    boolean roleFreeze(String id);

    boolean addRole(Role role);
}
