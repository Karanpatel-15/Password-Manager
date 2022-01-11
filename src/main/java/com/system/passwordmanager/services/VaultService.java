package com.system.passwordmanager.services;

import com.system.passwordmanager.models.Vault;
import com.system.passwordmanager.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaultService {

    @Autowired
    VaultRepository vaultRepository;

    public List<Vault> getAllCredentials(){
         return vaultRepository.findAll();
    }

    public void addCredential(Vault credential){
        vaultRepository.save(credential);
    }

    public void deleteCredentialById(Long id) {
        vaultRepository.deleteById(id);
    }

    public Optional<Vault> getCredentialById(Long id) {
        return vaultRepository.findById(id);
    }

    public void updateCredential(Vault credential, long id) {
        Optional<Vault> credentialToUpdate = vaultRepository.findById(id);
        Vault entity = credentialToUpdate.get();
        entity.setUsername(credential.getUsername());
        entity.setPassword(credential.getPassword());
        vaultRepository.save(entity);

    }
}
