package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.Role2UserService;
import com.sunjob.yudioj_springboot_framemark.vo.Role2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRoleController {
    @Autowired
    Role2UserService role2UserService;

    @RequestMapping("/userRoleModifySub")
    public String modifyUserRole(Role2User role2User){
      boolean result = role2UserService.modifyUserRole(role2User);
        return "redirect:menuGo?id=1634872940067";
    }
    @RequestMapping("/userRoleFreeze")
    @ResponseBody
    public String userRoleFreeze(@RequestParam("id") String id){
        boolean result = role2UserService.freezeRole2User(id);
        return result+"";
    }
    @RequestMapping("/adduserRole")
    public String adduserRole(Role2User role2User){
        boolean result = role2UserService.addUserRole(role2User);
     return "redirect:menuGo?id=1634872980195";
    }
}
