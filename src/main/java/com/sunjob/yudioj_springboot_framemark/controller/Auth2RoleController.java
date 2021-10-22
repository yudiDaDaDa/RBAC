package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.Auth2RoleService;
import com.sunjob.yudioj_springboot_framemark.vo.Auth2Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Auth2RoleController {
    @Autowired
    Auth2RoleService auth2RoleService;
    @RequestMapping("/authRoleModifySub")
    public String authRoleModifySub(Auth2Role auth2Role){
        boolean result = auth2RoleService.authRoleModify(auth2Role);
        return "redirect:menuGo?id=1634645703157";
    }
}
