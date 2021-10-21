package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.UserService;
import com.sunjob.yudioj_springboot_framemark.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("userLogin")
    public String userLogin(
            @RequestParam(value = "username" ,required = false) String name,
            @RequestParam(value = "password",required = false) String pwd,
            HttpServletRequest request,
            Model model){
        if(name==null || pwd==null) return "loginR";  //参数为空 返回登录页面
        User user = userService.userLogin(name,pwd);
        if(user ==null){
            model.addAttribute("msg","用户名或密码错误");
            return "loginR";
        }
        if(!user.getStatus().equals("正常")){
            model.addAttribute("msg","用户已被"+user.getStatus());
            return "loginR";
        }
        // 用户名或密码错误 用户被冻结或封禁 返回登录页面
        HttpSession session = request.getSession();
        session.setAttribute("user",user); //登录成功 跳转到主页
        return "redirect:/index";
    }
}
