package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.dto.*;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.FirebaseUserService;
import com.example.expenseTracker.expenseTracker.services.UserService;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final FirebaseUserService firebaseUserService;
    private String FIREBASE_URL="https://identitytoolkit.googleapis.com/v1/accounts";

    private String FIREBASE_API_KEY="AIzaSyAXdPpAuPZPz0A5jYksGzbHb4ORg6tCJHo";

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
        UserInput userInput=new UserInput();
        userInput.setEmail(user.getEmail());
        userInput.setPassword(user.getPassword());
        userInput.setName(input.getName());


        UserRecord userRecord=firebaseUserService.createUserInFireBase(userInput);
        user.setFirebaseId(userRecord.getUid());
        user=userRepository.save(user);
        UserOutputDto outputDto = modelMapper.map(user, UserOutputDto.class);
        return outputDto;
    }
    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        System.out.println(FIREBASE_URL);
        System.out.println(FIREBASE_API_KEY);
        String url = FIREBASE_URL+":signInWithPassword?key="+FIREBASE_API_KEY;
        Map<String, Object> map = new HashMap<>();
        map.put("email",loginInputDto.getUsername());
        map.put("password",loginInputDto.getPassword());
        map.put("returnSecureToken",true);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(map,httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        String body = exchange.getBody();
        Gson gson = new Gson();
        SignInFireBaseOutput signInFireBaseOutput = gson.fromJson(body,SignInFireBaseOutput.class);
        LoginOutputDto loginOutputDto = new LoginOutputDto();
        loginOutputDto.setAccessToken(signInFireBaseOutput.getIdToken());
        loginOutputDto.setRefreshToken(signInFireBaseOutput.getRefreshToken());
        loginOutputDto.setExpireIn(signInFireBaseOutput.getExpireIn());

        return loginOutputDto;
    }

    @Override
    public User getByFirebaseId(String uid) {

        return userRepository.findByFirebaseId(uid).orElseThrow(()-> new RuntimeException("firebase id not found"));
    }


}
