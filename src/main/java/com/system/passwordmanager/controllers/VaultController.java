package com.system.passwordmanager.controllers;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.models.Vault;
import com.system.passwordmanager.services.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vault")
public class VaultController {

    @Autowired
    VaultService vaultService;

    @GetMapping("/showdata")
    public String showData(Model model) {
        List<Vault> allCredentials = vaultService.getAllCredentials();
        model.addAttribute("credentials", allCredentials);
        model.addAttribute("masterUser", (MasterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "vault/showdata";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("credentialForm", new Vault());
        model.addAttribute("create","yes");
        return "vault/credentialForm";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute("credentialForm") Vault credential){
        vaultService.addCredential(credential);
        return "redirect:/vault/showdata";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("credentialForm", vaultService.getCredentialById(id));
        return "vault/credentialForm";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("credentialForm") Vault credential, @PathVariable Long id) {
        vaultService.updateCredential(credential,id);
        return "redirect:/vault/showdata";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        vaultService.deleteCredentialById(id);
        return "redirect:/vault/showdata";
    }

}