package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.Auth2Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Auth2RoleService {
    List<Auth2Role> findAllAuth2Role();
    List<Auth2Role> findAllAuth2RoleWithout();
}
