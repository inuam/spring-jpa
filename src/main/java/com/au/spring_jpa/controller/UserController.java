package com.au.spring_jpa.controller;

import com.au.spring_jpa.dto.AdminUserDto;
import com.au.spring_jpa.dto.CustomerUserDto;
import com.au.spring_jpa.dto.SimpleUserDto;
import com.au.spring_jpa.dto.UserDto;
import com.au.spring_jpa.entities.AdminUser;
import com.au.spring_jpa.entities.CustomerUser;
import com.au.spring_jpa.entities.UserEntity;
import com.au.spring_jpa.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@Valid @RequestBody UserDto dto) {
        if (dto instanceof SimpleUserDto simpleDto) {
            UserEntity user = new UserEntity();
            user.setUsername(simpleDto.getUsername());
            user.setPassword(simpleDto.getPassword());
            user.setUserType(simpleDto.getUserType());
            return userRepository.save(user);

        } else if (dto instanceof AdminUserDto adminDto) {
            AdminUser admin = new AdminUser();
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(adminDto.getPassword());
            admin.setPermissions(adminDto.getPermissions());
            admin.setUserType(adminDto.getUserType());
            return userRepository.save(admin);

        } else if (dto instanceof CustomerUserDto custDto) {
            CustomerUser customer = new CustomerUser();
            customer.setUsername(custDto.getUsername());
            customer.setPassword(custDto.getPassword());
            customer.setLoyaltyPoints(custDto.getLoyaltyPoints());
            customer.setUserType(custDto.getUserType());
            return userRepository.save(customer);

        }
        throw new IllegalArgumentException("Unsupported user type");
    }
}
