package com.system.passwordmanager.services;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.repository.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MasterUserService implements UserDetailsService {

    @Autowired
    MasterUserRepository masterUserRepository;

    public void addMasterUser(MasterUser masterUser) {
        masterUserRepository.save(masterUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return masterUserRepository.findByUsername(username);
    }
}
