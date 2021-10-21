package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.UserMapper;
import com.sunjob.yudioj_springboot_framemark.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public User userLogin(String name, String password) {
        User user =userMapper.userLogin(name,password);
        if(user == null) return null;
        authBuild(user);
        return user;
    }

    @Override
    public List<User> findALlUser() {
        return userMapper.findAllUser();
    }

    private void authBuild(User user) {
        user.setTempAuthSet(new HashSet<Auth>());
        Set<Role2User> role2UserSet= user.getRole2UserSet();
        for(Role2User cur : role2UserSet){
            if(cur.getRole()==null) break;
            Role role = cur.getRole();
            for(Auth2Role auth2Role:role.getAuth2RoleSet()){
               if(auth2Role.getAuth()!=null) user.getTempAuthSet().add(auth2Role.getAuth());
            }
        }
        return;
    }
}
