package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.services.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        try {
            masterUserService.addMasterUser(masterUser);
            return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/masteruser/signup";
        }
    }

    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirectAttributes){

        try {
            masterUserService.verifyUser(token);
        } catch (Exception e) {
            return "redirect:/verifiedlogin";
        }

        redirectAttributes.addFlashAttribute("verified", true);
        return "redirect:/login";
    }

}
