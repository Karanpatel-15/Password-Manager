package com.system.passwordmanager.repository;

import com.system.passwordmanager.models.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MasterUserRepository extends JpaRepository<MasterUser, Long> {

    MasterUser findByUsername(String username);

    MasterUser findByEmail(String email);

}
