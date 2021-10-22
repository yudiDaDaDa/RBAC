package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.UserMapper;
import com.sunjob.yudioj_springboot_framemark.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<User> findAllUserWithout() {
        return userMapper.finaAllUserWithout();
    }

    @Override
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public boolean modifyUser(User user) {
        user.setModifyTime(new Date());
        return userMapper.modifyUser(user);
    }

    @Override
    public boolean userFreeze(String id) {
        return userMapper.userFreeze(id,new Date());
    }

    @Override
    public boolean addUser(User user) {
        Date date = new Date();
        user.setModifyTime(date);
        user.setCreateTime(date);
        user.setId(System.currentTimeMillis()+"");
      return  userMapper.addUser(user);
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
