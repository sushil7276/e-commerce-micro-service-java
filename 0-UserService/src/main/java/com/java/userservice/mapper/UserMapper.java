package com.java.userservice.mapper;

import com.java.userservice.dot.UserDto;
import com.java.userservice.module.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email",target = "emailAddress")
    @Mapping(source = "phone",target = "contact")
    UserDto toDto(User user);

    @Mapping(source = "emailAddress",target = "email")
    @Mapping(source = "contact",target = "phone")
    User toEntity(UserDto userDto);
}
