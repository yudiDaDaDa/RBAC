package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User userLogin(String name,String password);
    List<User> findALlUser();
}
