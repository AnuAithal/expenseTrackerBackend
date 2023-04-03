package com.example.expenseTracker.expenseTracker.services;

import com.example.expenseTracker.expenseTracker.dto.UserInput;
import com.google.firebase.auth.UserRecord;

public interface FirebaseUserService {
	UserRecord createUserInFireBase(UserInput input);
}
