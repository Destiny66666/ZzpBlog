package com.zzp.controller.blog;

import com.zzp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:27
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "blog/login";
    }

    @RequestMapping("/toLogin")
    public String toLogin(String username, String password, Model model, HttpServletResponse response){

        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);//执行登录的方法，如果没有异常就OK了
            return "redirect:/admin/blogs";
        }catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名错误");
            return "blog/login";
        }catch (IncorrectCredentialsException e){   //密码不存在
            model.addAttribute("msg","密码错误");
            return "blog/login";
        }

    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }
}
