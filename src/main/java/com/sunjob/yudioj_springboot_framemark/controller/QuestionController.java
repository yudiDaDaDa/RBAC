package com.sunjob.yudioj_springboot_framemark.controller;

import com.sunjob.yudioj_springboot_framemark.service.QuestionService;
import com.sunjob.yudioj_springboot_framemark.vo.Question;
import com.sunjob.yudioj_springboot_framemark.vo.Task;
import com.sunjob.yudioj_springboot_framemark.vo.User;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @RequestMapping("/quGo")
    public String questionGo(@RequestParam("id") String id,
                             HttpServletRequest request,
                             Model model){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Question question =  questionService.selectById(id);
        if(question == null) return "menuIndex";
        if(question.getStatus().equals("冻结")) {
            model.addAttribute("msg","该问题已被冻结");
            return "erro";
        }
        model.addAttribute("question",question);
        return "questionSub";
    }

    @RequestMapping("questionTest")
    @ResponseBody
    public String questionTest(@RequestParam("code") String code,
                               @RequestParam("id") String q_id,
                               HttpServletRequest request){;
         HttpSession session = request.getSession();
         User user = (User)session.getAttribute("user");
       Question question = questionService.selectById(q_id);
        if(question==null) return "{erroCode:404}";
        if(question.getStatus().equals("冻结")) return "{erroCode:100}";
        String res = questionService.codeTest(question,code,user.getId(), Task.type.UserTest);
        return res;
    }

    @RequestMapping("/ansBuild")
    public String getAns(@RequestParam(value = "id",required = false) String id,
                         @RequestParam(value = "code",required = false) String code,
                         @RequestParam(value = "test",required = false) String judge,
                         HttpServletRequest request){
        if(id ==null || id.trim().length()==0)return "{erroCode:404}";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(code==null || judge == null) return "{errCode:300}";
        Question question = questionService.selectById(id);
        if(question==null) return "{erroCode:404}";
        String res = questionService.codeTest(question,code,user.getId(), Task.type.AnsBuild);
        return res;
    }
}
