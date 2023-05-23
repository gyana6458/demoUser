package com.demo.service;


import com.demo.dto.UserDto;
import entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
    User getName(String name);
}
