package com.java.userservice.servie;

import com.java.userservice.dot.CredentialDto;
import com.java.userservice.mapper.CredentialMapper;
import com.java.userservice.repositoy.CredentialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private CredentialMapper credentialMapper;

    public CredentialDto findByUsernameAndPassword(String username, String password) {
        CredentialDto dto = credentialMapper.toDto(credentialRepository.findByUsernameAndPassword(username, password));
        if (dto == null) {
            throw new RuntimeException("Username or password incorrect");
        }
        return dto;
    }

    public CredentialDto findByUsername(String username) {
        return credentialRepository.findByUsername(username)
                .map(credentialMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Username not found"));
    }
}
