package com.tth.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("title","hello thymeleaf");
        model.addAttribute("info","this is first thymeleaf test");
        return "hello";
    }
}
