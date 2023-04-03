package com.example.expenseTracker.expenseTracker.dto;

import lombok.Data;

@Data
public class SignInFireBaseOutput {

	private String idToken;
	private String email;
	private String refreshToken;
	private String expireIn;
	private String localId;
	private boolean registered;
}
