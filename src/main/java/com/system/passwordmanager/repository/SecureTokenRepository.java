package com.system.passwordmanager.repository;

import com.system.passwordmanager.models.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(String token);

    Long removeByToken(String token);

}
