package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.Passwords;
import com.system.passwordmanager.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entry")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @GetMapping("/showdata")
    public String showData(Model model) {

        List<Passwords> allPasswords = passwordService.getAllPasswords();
        model.addAttribute("passwords", allPasswords);
        return "entry/showdata";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("passwordForm", new Passwords());
        return "entry/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("passwordForm") Passwords password) {
        System.out.println(password.getUsername());
        passwordService.addPassword(password);
        return "redirect:/entry/showdata";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
          passwordService.deletePasswordById(id);
        return "redirect:/entry/showdata";
    }

}
