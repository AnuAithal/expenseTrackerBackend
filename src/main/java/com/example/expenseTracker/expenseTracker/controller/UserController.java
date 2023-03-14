package com.example.expenseTracker.expenseTracker.controller;

import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User result=userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user" + result.getId())).body(result);
    }


}
