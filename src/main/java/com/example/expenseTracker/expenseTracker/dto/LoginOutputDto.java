package com.example.expenseTracker.expenseTracker.dto;

import lombok.Data;

@Data
public class LoginOutputDto {
    private String accessToken;
    private String refreshToken;
    private String expireIn;
}
