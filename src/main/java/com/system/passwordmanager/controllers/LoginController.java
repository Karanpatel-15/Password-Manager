package com.system.passwordmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/verifiedlogin")
    public String verifiedLogin(Model model){
        model.addAttribute("verified", true);
        System.out.println("added to model");
        return "login";
    }

}
