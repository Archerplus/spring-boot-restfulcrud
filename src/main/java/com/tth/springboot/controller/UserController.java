package com.tth.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @PostMapping("/user/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        Model model, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功，把用户信息方法哦session中，防止表单重复提交，重定向到后台页面
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        //登录失败,返回到登录页面
        model.addAttribute("msg", "用户名或密码错误！");
        return "login";
    }
}
