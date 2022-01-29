package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.services.MasterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/masteruser")
public class MasterUserController {

    final
    MasterUserService masterUserService;

    public MasterUserController(MasterUserService masterUserService) {
        this.masterUserService = masterUserService;
    }


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
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirAttr){
        if(StringUtils.isEmpty(token)){
//            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.missing.token", null,LocaleContextHolder.getLocale()));
            return "redirect:/login";
        }
        try {
            masterUserService.verifyUser(token);
        } catch (Exception e) {
//            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
            return "redirect:/login";
        }

//        redirAttr.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null,LocaleContextHolder.getLocale()));
        return "redirect:/login";
    }

}
