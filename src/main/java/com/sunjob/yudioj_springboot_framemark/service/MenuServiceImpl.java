package com.sunjob.yudioj_springboot_framemark.service;

import com.sunjob.yudioj_springboot_framemark.mapper.MenuMapper;
import com.sunjob.yudioj_springboot_framemark.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService{
     static Random random;
    static{
    random = new Random();
    }
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> findMenuList(Set<Auth> authSet) {
        if(authSet==null || authSet.size()==0) return null; //该角色没有权限
        List<Menu> menuList = new ArrayList<Menu>();
        for(Auth cur : authSet){
            List<Menu> now =menuMapper.findMenuByAuthId(cur.getId());
            if(now.size()>0) menuList.add(now.get(0));
        }
        List<Menu> res = buildTree(menuList);
        return res;
    }

    public Menu findMenuById(Set<Auth> authSet,String id){
       Menu menu = menuMapper.findMenuById(id);
       if(menu==null) return null;
       if(authSet.contains(menu.getAuth()))
       return menu;
       else return null;
    }
    @Override
    public List<Menu> selectMenuAll() {
        return menuMapper.selectMenuAll();
    }

    @Override
    public List<Menu> selectMenuAllWithout() {
        return menuMapper.selectMenuAllWithout();
    }

    @Override
    public Menu findModifyMenuById(Set<Auth> authSet, String id) {
        Menu menu = menuMapper.findMenuById("4");
        if(menu == null) return null;
        if(authSet.contains(menu.getAuth())){
            Menu cur = menuMapper.findMenuById(id);
            return cur;
        }
        return null;
    }

    @Override
    public boolean menuModifySub(Menu menu) {
        if(menu ==null) return false;
        menu.setModifyTime(new Date()); //定义修改时间
        return menuMapper.menuModifySub(menu);
    }

    @Override
    public Menu findModifyMenuByIdWithout(String id) {
        return menuMapper.findMenuByIdWithout(id);
    }

    @Override
    public boolean freezeMenu(String id,Date modifyTime) {
        return menuMapper.freezeMenu(id,modifyTime);
    }

    @Override
    public boolean addMenu(Menu menu) {
        menu.setId(System.currentTimeMillis()+"");
        Date date = new Date();
        menu.setModifyTime(date);
        menu.setCreateTime(date);
        return menuMapper.addMenu(menu);
    }

    List<Menu> buildTree(List<Menu> menuList){
        Map<String,Menu> data = new HashMap<String,Menu>();
        List<Menu> res = new ArrayList<Menu>();
        for(Menu cur:menuList) {
            cur.setChilds(new ArrayList<Menu>());
            data.put(cur.getId(),cur);
        }
        for(Menu cur:menuList){
            if(cur.getParentId().equals("indexMenu")) res.add(cur);
            else {
                if(data.containsKey(cur.getParentId()))
                data.get(cur.getParentId()).getChilds().add(cur);
            }
        }
        return res;
    }


}
