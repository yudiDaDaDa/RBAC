package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.AuthService;
import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;
    @RequestMapping("/authModifySub")
    public String modifyAuthSub(Auth auth){
        boolean result = authService.modifyAuth(auth);
        return "redirect:menuGo?id=11";
    }
    @RequestMapping("/authFreeze")
    @ResponseBody
    public String authFreeze(@RequestParam("id") String id){
        boolean result = authService.authFreeze(id,new Date());
        return ""+result;
    }
    @RequestMapping("/addAuth")
    public String addAuth(Auth auth){
        boolean result = authService.addAuth(auth);
        return "redirect:menuGo?id=10";
    }

}
