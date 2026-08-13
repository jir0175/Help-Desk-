package com.example.demo.service;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegisterDto registerDto);
}
