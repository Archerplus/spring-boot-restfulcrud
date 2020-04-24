package com.tth.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("hello");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            System.out.println("loginUser is null");
            //未登录，拦截，并转发到登录界面
            request.setAttribute("msg","您还没有登录，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        System.out.println("loginUser is not null");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
