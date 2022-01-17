package com.system.passwordmanager.repository;

import com.system.passwordmanager.models.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaultRepository extends JpaRepository<Vault, Long> {

    List<Vault> findByMasterId(Long id);

}
