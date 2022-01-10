package com.system.passwordmanager.repository;

import com.system.passwordmanager.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

}
