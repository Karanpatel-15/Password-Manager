package com.system.passwordmanager.services;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.repository.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    MasterUserRepository masterUserRepository;

    public MasterUser getCurrentLoggedInUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MasterUser masterUser = masterUserRepository.findByUsername(userDetails.getUsername());
        return masterUser;
    }

}
