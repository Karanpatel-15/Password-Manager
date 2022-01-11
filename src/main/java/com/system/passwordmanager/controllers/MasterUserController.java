package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.services.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/masteruser")
public class MasterUserController {

    @Autowired
    MasterUserService masterUserService;


    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("signupForm", new MasterUser());
        return "masteruser/signup";
    }

    @PostMapping("/signup")
    public String newUser(@ModelAttribute("signupForm") MasterUser masterUser) {
        System.out.println(masterUser.getUsername());
        masterUserService.addMasterUser(masterUser);
        return "redirect:/login";
    }

    //    @GetMapping("/")
//    public String index() {
//        return ("index");
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return ("user");
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return ("admin");
//    }


}
