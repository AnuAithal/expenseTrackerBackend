package com.example.expenseTracker.expenseTracker.dto;

import lombok.Data;

@Data
public class FirebaseUserInputDto {

    private String name;
    private String email;
    private String password;
}
