package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AuthService {
    List<Auth> findAllAuth();
    Auth findAuthById(String id);
    boolean modifyAuth(Auth auth);
    Auth findAuthByIdWithout(String id);
    boolean authFreeze(String id, Date date);
    List<Auth> findALlAuthWithout();
    boolean addAuth(Auth auth);
}
