package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import com.sunjob.yudioj_springboot_framemark.vo.Menu;
import com.sunjob.yudioj_springboot_framemark.vo.Role;
import com.sunjob.yudioj_springboot_framemark.vo.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public interface MenuService {
    List<Menu> findMenuList(Set<Auth> authSet);
    Menu findMenuById(Set<Auth> authSet,String id);
    List<Menu> selectMenuAll();
    List<Menu> selectMenuAllWithout();
    Menu findModifyMenuById(Set<Auth> authSet,String id);
    boolean menuModifySub(Menu menu);

    Menu findModifyMenuByIdWithout(String id);
    boolean freezeMenu(String id, Date modify_Time);
    boolean addMenu(Menu menu);
}
