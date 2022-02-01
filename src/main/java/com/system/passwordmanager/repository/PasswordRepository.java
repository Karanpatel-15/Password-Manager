package com.system.passwordmanager.repository;

import com.system.passwordmanager.models.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Passwords, Long> {

    List<Passwords> findByUsername(String username);

}
