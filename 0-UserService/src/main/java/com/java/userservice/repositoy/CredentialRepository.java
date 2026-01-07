package com.java.userservice.repositoy;

import com.java.userservice.module.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential,Integer> {

    Credential findByUsernameAndPassword(String username, String password);
    Optional<Credential> findByUsername(String username);
}
