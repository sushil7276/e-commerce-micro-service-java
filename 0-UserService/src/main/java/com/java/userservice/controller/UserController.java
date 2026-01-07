package com.java.userservice.controller;

import com.java.userservice.dot.UserDto;
import com.java.userservice.servie.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.info("UserController :: createUser {}", userDto.getEmailAddress());
        return userService.save(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable Integer userId) {
        log.info("UserController :: findById {}", userId);
        return userService.findById(userId);
    }

    @GetMapping("/exits/{userId}")
    public boolean isUserExits(@PathVariable Integer userId) {
        return userService.existsById(userId);
    }


    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
        return userService.update(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable Integer userId) {
        log.info("UserController :: deleteUser {}", userId);
        return userService.delete(userId);
    }
}
