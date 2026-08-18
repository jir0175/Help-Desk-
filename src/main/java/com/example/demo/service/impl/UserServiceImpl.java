package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Transactional
@Service
@RequiredArgsConstructor
public class  UserServiceImpl implements UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public UserResponseDto registerUser(UserRegisterDto registerDto){
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new UserAlreadyExistsException("Email is already taken!");
        };
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new UserAlreadyExistsException("Username is already taken!");
        }
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setLastName(savedUser.getLastName());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setFirstName(savedUser.getFirstName());
        responseDto.setId(savedUser.getId());
        responseDto.setRoles(Set.of(role.getName()));
        return responseDto;
    }

}
