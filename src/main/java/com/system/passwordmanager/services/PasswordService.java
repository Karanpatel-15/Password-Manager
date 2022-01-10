package com.system.passwordmanager.services;

import com.system.passwordmanager.models.Passwords;
import com.system.passwordmanager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    public List<Passwords> getAllPasswords(){
         return passwordRepository.findAll();
    }

    public void addPassword(Passwords password){
        passwordRepository.save(password);
    }

    public void deletePasswordById(Long id) {
        passwordRepository.deleteById(id);
    }
}
