package com.example.sweater.controller;

import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private
    @GetMapping("/registration")
    public String registration(){
        return "registration";// будет просто возвращать view
    }

    @PostMapping("/registration")
    public String addUser(User user){
        return "redirect:/login"; //переходи на страницу login
    }




}
