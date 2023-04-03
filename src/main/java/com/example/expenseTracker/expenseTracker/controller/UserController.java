package com.example.expenseTracker.expenseTracker.controller;

import com.example.expenseTracker.expenseTracker.dto.LoginInputDto;
import com.example.expenseTracker.expenseTracker.dto.LoginOutputDto;
import com.example.expenseTracker.expenseTracker.dto.UserInputDto;
import com.example.expenseTracker.expenseTracker.dto.UserOutputDto;
import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Data
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping
    public List<UserOutputDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserOutputDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping()
    public UserOutputDto createUser(@RequestBody UserInputDto input){
        return userService.createUser(input);
    }

    @PostMapping("/login")
    public LoginOutputDto login(@RequestBody LoginInputDto loginInputDto)
    {
        return userService.login(loginInputDto);
    }

}
