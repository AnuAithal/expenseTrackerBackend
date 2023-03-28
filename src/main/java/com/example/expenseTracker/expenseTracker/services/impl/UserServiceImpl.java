package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.dto.UserInputDto;
import com.example.expenseTracker.expenseTracker.dto.UserOutputDto;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserOutputDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserOutputDto>>(){}.getType();
        List<UserOutputDto> dtoUserList = modelMapper.map(users, listType);
        return dtoUserList;
    }

    public UserOutputDto getUserById(Long id){
        User user=userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return modelMapper.map(user, UserOutputDto.class);
    }

    @Override
    public UserOutputDto createUser(UserInputDto input){
        User user=modelMapper.map(input, User.class);
        user=userRepository.save(user);
        UserOutputDto outputDto = modelMapper.map(user, UserOutputDto.class);
        return outputDto;
    }


}
