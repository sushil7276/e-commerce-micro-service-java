package com.java.userservice.dot;

import com.java.userservice.module.RoleBasedAuthority;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredentialDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private RoleBasedAuthority roleBasedAuthority;
}
