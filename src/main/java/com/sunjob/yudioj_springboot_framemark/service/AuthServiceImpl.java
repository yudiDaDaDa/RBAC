package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.AuthMapper;
import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    AuthMapper authMapper;
    @Override
    public List<Auth> findAllAuth() {
         return authMapper.findALlAuth();
    }

    @Override
    public Auth findAuthById(String id) {
        return authMapper.findAuthById(id);
    }
    public Auth findAuthByIdWithout(String id){
        return authMapper.findAuthByIdWithout(id);
    }

    @Override
    public boolean authFreeze(String id,Date date) {
        return  authMapper.authFreeze(id,date);
    }

    @Override
    public List<Auth> findALlAuthWithout() {
        return authMapper.findAllAuthWithout();
    }

    @Override
    public boolean addAuth(Auth auth) {
        Date date = new Date();
        auth.setModifyTime(date);
        auth.setCreateTime(date);
        auth.setId(System.currentTimeMillis()+"");
        return authMapper.addAuth(auth);
    }

    @Override
    public boolean modifyAuth(Auth auth) {
        auth.setModifyTime(new Date());
        return authMapper.modifyAuthSub(auth);
    }
}
