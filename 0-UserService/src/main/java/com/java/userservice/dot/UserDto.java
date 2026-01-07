package com.java.userservice.dot;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Integer userId;
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    private String emailAddress;
    private String contact;

    private CredentialDto credential;
}
