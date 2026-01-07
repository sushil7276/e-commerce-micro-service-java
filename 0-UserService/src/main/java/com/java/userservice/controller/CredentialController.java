package com.java.userservice.controller;

import com.java.userservice.dot.CredentialDto;
import com.java.userservice.servie.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credentials")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @GetMapping("/username/{uname}")
    public CredentialDto getCredentialByUsername(@PathVariable("uname") String username) {
        return credentialService.findByUsername(username);

    }

    @GetMapping("/login")
    public CredentialDto getCredentialByUsernameAndPassword(@RequestParam String username,
                                                            @RequestParam String password) {
        return credentialService.findByUsernameAndPassword(username, password);
    }
}
