package com.system.passwordmanager.services;

import com.system.passwordmanager.models.MasterUser;
import com.system.passwordmanager.models.Vault;
import com.system.passwordmanager.repository.MasterUserRepository;
import com.system.passwordmanager.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaultService {

    final
    VaultRepository vaultRepository;

    final
    MasterUserRepository masterUserRepository;

    final
    LoginService loginService;

    public VaultService(VaultRepository vaultRepository, MasterUserRepository masterUserRepository, LoginService loginService) {
        this.vaultRepository = vaultRepository;
        this.masterUserRepository = masterUserRepository;
        this.loginService = loginService;
    }

    public List<Vault> getAllCredentials(){
        return vaultRepository.findByMasterId(loginService.getCurrentLoggedInUser().getId());
    }

    public void addCredential(Vault credential){
        MasterUser masterUser = loginService.getCurrentLoggedInUser();
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
