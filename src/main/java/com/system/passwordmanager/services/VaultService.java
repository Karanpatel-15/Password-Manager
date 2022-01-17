package com.system.passwordmanager.services;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.models.Vault;
import com.system.passwordmanager.repository.MasterUserRepository;
import com.system.passwordmanager.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaultService {

    @Autowired
    VaultRepository vaultRepository;

    @Autowired
    MasterUserRepository masterUserRepository;


    public List<Vault> getAllCredentials(){
        MasterUser masterUser = (MasterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return vaultRepository.findByMasterId(masterUser.getId());
    }

    public void addCredential(Vault credential){
        MasterUser masterUser = (MasterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        credential.setMasterId(masterUser.getId());
        vaultRepository.save(credential);
    }

    public void deleteCredentialById(Long id) {
        vaultRepository.deleteById(id);
    }

    public Vault getCredentialById(Long id) {
        Optional<Vault> credential = vaultRepository.findById(id);
        Vault entity = credential.get();
        return entity;
    }

    public void updateCredential(Vault credential, long id) {
        Optional<Vault> credentialToUpdate = vaultRepository.findById(id);
        Vault entity = credentialToUpdate.get();
        entity.setWebsite(credential.getWebsite());
        entity.setUsername(credential.getUsername());
        entity.setPassword(credential.getPassword());
        vaultRepository.save(entity);
    }
}
