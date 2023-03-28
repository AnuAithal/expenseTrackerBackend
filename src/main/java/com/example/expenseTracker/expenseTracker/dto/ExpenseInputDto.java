package com.example.expenseTracker.expenseTracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseInputDto {

    private LocalDateTime expenseDate;
    private String description;
    private String amount;
    private Long categoryId;
    private Long userId;
}
