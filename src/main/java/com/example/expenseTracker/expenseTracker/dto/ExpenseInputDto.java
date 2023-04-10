package com.example.expenseTracker.expenseTracker.dto;

import com.example.expenseTracker.expenseTracker.model.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseInputDto {

//    private Long id;
    private LocalDateTime expenseDate;
    private String description;
    private String amount;
    private Long categoryId;
//    private Long userId;
}
