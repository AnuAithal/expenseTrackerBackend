package com.example.expenseTracker.expenseTracker.services;

import com.example.expenseTracker.expenseTracker.dto.LoginInputDto;
import com.example.expenseTracker.expenseTracker.dto.LoginOutputDto;
import com.example.expenseTracker.expenseTracker.dto.UserInputDto;
import com.example.expenseTracker.expenseTracker.dto.UserOutputDto;
import com.example.expenseTracker.expenseTracker.model.User;
import lombok.Data;

import java.util.List;
public interface UserService {

    public List<UserOutputDto> getAllUsers();
    public UserOutputDto getUserById(Long id);
    public UserOutputDto createUser(UserInputDto input);

    public LoginOutputDto login(LoginInputDto loginInputDto);

    User getByFirebaseId(String uid);

}
