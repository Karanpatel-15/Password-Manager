package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.Users;
import com.system.passwordmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return ("index");
    }

    @GetMapping("/index.html")
    public String home() {
        return ("index");
    }

    @GetMapping("/user")
    public String user() {
        return ("user");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("admin");
    }

    @RequestMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("passwordForm", new Users());
        return "signup";
    }

    @PostMapping("/newuser")
    public String newUser(@ModelAttribute("passwordForm") Users user) {
        System.out.println(user.getUsername());
        userService.addUser(user);
        return "redirect:/login";
    }



}
