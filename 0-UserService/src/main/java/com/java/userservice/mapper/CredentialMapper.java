package com.java.userservice.mapper;

import com.java.userservice.dot.CredentialDto;
import com.java.userservice.module.Credential;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

    CredentialDto toDto(Credential credential);

}
