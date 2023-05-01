package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.model.User;
import org.springframework.stereotype.Service;


import com.example.expenseTracker.expenseTracker.dto.UserInput;
import com.example.expenseTracker.expenseTracker.services.FirebaseUserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FirebaseUserServiceImpl implements FirebaseUserService {



	@Override
	public UserRecord createUserInFireBase(UserInput input) {
		log.debug("Trying to create user in firebase");
		UserRecord.CreateRequest request=new UserRecord.CreateRequest()
				.setEmail(input.getEmail())
				.setPassword(input.getPassword())
				.setDisplayName(input.getName())
				.setDisabled(false);
		try{
			return FirebaseAuth.getInstance().createUser(request);
		} catch (FirebaseAuthException e){
			log.error("Something went wrong:{} ", e.getMessage());
			throw new RuntimeException();
		}
	}
}
