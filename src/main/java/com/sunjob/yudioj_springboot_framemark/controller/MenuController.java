package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.*;
import com.sunjob.yudioj_springboot_framemark.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    Auth2RoleService auth2RoleService;
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();   //根据登录获取的用户进行菜单查询
        User user = (User)session.getAttribute("user");
       List<Menu> menuList = menuService.findMenuList(user.getTempAuthSet());
       model.addAttribute("menuList",menuList);
        return "index";
    }

    @RequestMapping("/menuGo")
    public String menuGo(@RequestParam("id") String id,
                         HttpServletRequest request, Model model){
       HttpSession session = request.getSession();
       User user = (User) session.getAttribute("user");
       Menu menu = menuService.findMenuById(user.getTempAuthSet(),id);
       if(menu == null ) return "menuIndex";
       switch(menu.getId()){
           case "5":model.addAttribute("menuList",menuService.selectMenuAll());
           break;
           case "4": model.addAttribute("menuList",menuService.selectMenuAllWithout());
           break;
           case "10":model.addAttribute("authList",authService.findAllAuth());
               break;
           case "11": model.addAttribute("authList",authService.findALlAuthWithout());
           break;
           case "8":
           case "7":
           case "1":model.addAttribute("questionList",questionService.selectAll());
           break;
           case "13":
           case "12":model.addAttribute("userList",userService.findALlUser());
           break;
           case "1634645800287": model.addAttribute("role2authList",auth2RoleService.findAllAuth2Role());
           break;
           case "1634645703157": model.addAttribute("role2authList",auth2RoleService.findAllAuth2RoleWithout());

       }
       return menu.getUrl();
    }
    @RequestMapping("/modifyQst")
    public String modifyQst(@RequestParam("id") String id,
                            HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Question question = questionService.selectById(id);
        if(question == null){
            model.addAttribute("msg","该问题寻找不到");
            return "erro";
        }
        model.addAttribute("question",question);
        return "questionMgr/qstModify";
    }

    @RequestMapping("/menuModify")
    public String menuModify(@RequestParam("id") String id,HttpServletRequest request,
                             Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Menu menu = menuService.findModifyMenuByIdWithout(id);
        if(menu == null) return "menuIndex";
        model.addAttribute("modifyMenu",menu);
        return "menuMgr/menuModify";
    }

    @RequestMapping("/menuModifySub")
    public String menuModifySub(Menu menu ){
        boolean result = menuService.menuModifySub(menu);
        return "redirect:/menuGo?id=4";
    }
   @RequestMapping("/menuFreeze")
   @ResponseBody
    public String menuFreeze(@RequestParam("id") String id){
       boolean result = menuService.freezeMenu(id,new Date());
       return result+"";
   }

  @RequestMapping("/addMenu")
    public String addMenu(Menu menu){
        boolean result = menuService.addMenu(menu);
        return "redirect:menuGo?id=5";
  }
  @RequestMapping("/modifyAuth")
    public String modifyAuth(@RequestParam("id") String id, Model model){
        Auth auth = authService.findAuthByIdWithout(id);
        if(auth == null) return "menuIndex";
        model.addAttribute("auth",auth);
        return "authMgr/authModify";
  }
  @RequestMapping("/modifyRoleAuth")
    public String modifyRoleAuth(@RequestParam("id") String id,Model model ){
        Auth2Role auth2Role = auth2RoleService.findAuth2RoleByIdWithout(id);
        if(auth2Role == null) return "menuIndex";
        model.addAttribute("authRole",auth2Role);
        return "roleAuthMgr/roleAuthModify";
  }

}
