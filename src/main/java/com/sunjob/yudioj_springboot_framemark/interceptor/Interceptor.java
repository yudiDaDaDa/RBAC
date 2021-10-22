package com.sunjob.yudioj_springboot_framemark.interceptor;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sunjob.yudioj_springboot_framemark.service.AuthService;
import com.sunjob.yudioj_springboot_framemark.vo.Auth;
import com.sunjob.yudioj_springboot_framemark.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


public class Interceptor implements HandlerInterceptor {
    @Autowired
    AuthService authService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!isUserNull(request,response)) return false; //判断用户是否登录 未登录重定向到登录界面
        if(!isAuthNull(request,response)) return false; // 权限验证
        String url = request.getRequestURI();
        System.out.println(url);
        //根据路径进行不同逻辑的前置判断
        switch (url){
            case "/modifyUserRole":
            case "/menuGo":
            case "/modifyQst":
            case "/menuModify":
            case "/menuFreeze":
            case "/modifyAuth":
            case "/authFreeze":
            case "/modifyRoleAuth":
            case "/authRoleFreeze":
            case "/userRoleFreeze":
            case "/modifyUser":
            case "/userFreeze":
            case "/quGo":return  isparamIdNull(request,response);
            case "/questionTest": return isquestionTestParamNull(request,response);
            case "/menuModifySub" : return isparamIdNull(request,response)&& isMenuParamNull(request,response);
            case "/addMenu":return isMenuParamNull(request,response);
            case "/addAuth":return isAuthNull(request,response);
            case "/authModifySub": return isparamIdNull(request,response)&&isAuthParamNull(request,response);
            case "/authRoleModifySub":return isparamIdNull(request,response)&&isAuthRoleParamNull(request,response);
            case "/addauthRole": return isAuthRoleParamNull(request,response);
            case "/userRoleModifySub": return isparamIdNull(request,response)&&isUserRoleParamNull(request,response);
            case "/adduserRole" :return isUserRoleParamNull(request,response);
            case "/userModifySub":return isparamIdNull(request,response)&&isUserParamNull(request,response);
            case "/addUser" :return isUserParamNull(request,response);
        }
        return true;
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private boolean isUserNull(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();   //根据登录获取的用户进行菜单查询
        User user = (User)session.getAttribute("user");
        String tag = request.getHeader("X-Requested-With");
        if(tag!=null && tag.equals("XMLHttpRequest")){
            if(user == null){
               response.getWriter().println("{erroCode:404}");
                return false;
            }
        }
        if(user == null){
            response.sendRedirect("login.html");
            return false;
        }
        return true;
    }
    private boolean isparamIdNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id==null || id.trim().length()==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }
    private boolean isquestionTestParamNull(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String code = request.getParameter("code");
     String id = request.getParameter("id");
     if(code==null || code.trim().length()==0 || id==null || id.trim().length()==0){
         response.getWriter().println("{errorCode:200}");
         return false;
     }
     return true;
    }
    private boolean isMenuParamNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String name = request.getParameter("name");
        String parentId = request.getParameter("parentId");
        String authId = request.getParameter("authId");
        String status = request.getParameter("status");
        if( url == null || url.trim().length() ==0 ||
                name ==null || name.trim().length() ==0 ||
                authId==null || authId.trim().length()==0 ||
        parentId == null || parentId.trim().length()==0 ||
        status == null || status.trim().length() ==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }

    private boolean isAuthNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("user");
        Set<Auth> authSet = user.getTempAuthSet();
        Auth auth = null;
        switch (url){
            case "/menuModifySub" :
                auth = authService.findAuthById("4");
               break;
            case "/menuFreeze":
            case "/addMenu":
                auth = authService.findAuthById("5");
                if(auth!=null&&!authSet.contains(auth)) {
                    response.getWriter().println("{'result':'404'}");
                    return false;
                }
                break;
        }
        if(auth!=null&&!authSet.contains(auth)) {
            request.getRequestDispatcher("menuIndex").forward(request,response);
            return false;
        }
        return true;
    }

    private boolean isAuthParamNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        if(name ==null || name.trim().length() ==0 ||
                status == null || status.trim().length() ==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }
    private boolean isAuthRoleParamNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authId = request.getParameter("authId");
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        if(status==null || status.trim().length()==0||authId==null || authId.trim().length()==0 || roleId == null || roleId.trim().length()==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }
    private boolean isUserRoleParamNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        if(status==null || status.trim().length()==0||userId==null || userId.trim().length()==0 || roleId == null || roleId.trim().length()==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }
    private boolean isUserParamNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String status = request.getParameter("status");
        if(name == null || name.trim().length()==0 || pwd==null || pwd.trim().length()==0 || status==null || status.trim().length()==0){
            request.getRequestDispatcher("menuIndex.html").forward(request,response);
            return false;
        }
        return true;
    }
}
