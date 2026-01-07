package com.java.userservice.servie;

import com.java.userservice.dot.UserDto;

public interface UserService {

    UserDto save(UserDto userDto);
    UserDto findById(Integer userId);
    boolean existsById(Integer userId);
    UserDto update(Integer userId,UserDto user);
    boolean delete(Integer userId);
}
