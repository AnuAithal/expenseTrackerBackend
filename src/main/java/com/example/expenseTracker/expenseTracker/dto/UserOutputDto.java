package com.example.expenseTracker.expenseTracker.dto;


import lombok.Data;

@Data
public class UserOutputDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}
