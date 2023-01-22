package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    List<UserDto> fetchAllUsers();

    UserDto fetchUserById(Long userId);

    UserDto updateUser(UserDto userDto);

    String removeUserById(Long userId);

    String removeAllUser();

}
