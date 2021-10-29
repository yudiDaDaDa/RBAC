package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.RoleService;
import com.sunjob.yudioj_springboot_framemark.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
   @RequestMapping("/roleModify")
    public String roleModify(@RequestParam("id") String id, Model model){
         model.addAttribute("role",roleService.getRoleById(id));
       return "roleMgr/roleModify";
   }

   @RequestMapping("/roleModifySub")
    public String roleModifySub(Role role){
        roleService.modifyRole(role);
       return "redirect:menuGo?id=1635504753956";
   }
   @RequestMapping("/roleFreeze")
    @ResponseBody
    public String roleFreeze(@RequestParam("id") String id){
       return roleService.roleFreeze(id)+"";
   }
   @RequestMapping("/addRole")
    public String addRole(Role role){
       boolean result = roleService.addRole(role);
       return "redirect:menuGo?id=1635504787725";
   }
}
