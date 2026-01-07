package com.java.userservice.servie;

import com.java.userservice.dot.UserDto;
import com.java.userservice.mapper.UserMapper;
import com.java.userservice.module.Credential;
import com.java.userservice.module.User;
import com.java.userservice.repositoy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Credential credential = user.getCredential();
        // TODO : we are providing original password in db but we need to store in encoded password

        // BiDirectional
        credential.setUser(user);
        User dbUser = userRepository.save(user);

        return userMapper.toDto(dbUser);
    }

    @Override
    public UserDto findById(Integer userId) {
        return userRepository.findById(userId).map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

    }

    @Override
    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDto update(Integer userId, UserDto user) {
        User userDb = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getContact());
        userDb.setPhone(user.getContact());

        Credential credential = userDb.getCredential();
        credential.setUsername(user.getCredential().getUsername());
        credential.setPassword(user.getCredential().getPassword());
        credential.setRoleBasedAuthority(user.getCredential().getRoleBasedAuthority());
        credential.setUser(userDb);

        User save = userRepository.save(userDb);

        return userMapper.toDto(save);
    }

    @Override
    public boolean delete(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
