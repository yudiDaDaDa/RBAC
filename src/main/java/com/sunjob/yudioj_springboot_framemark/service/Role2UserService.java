package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.Role2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Role2UserService {
    List<Role2User> findAllRole2UserWithout();
    boolean modifyUserRole(Role2User role2User);
    Role2User findRole2UserById(String id);
    List<Role2User> findAllRole2User();
    boolean freezeRole2User(String id);
    boolean addUserRole(Role2User role2User);
}
