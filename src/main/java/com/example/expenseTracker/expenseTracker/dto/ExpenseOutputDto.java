package com.example.expenseTracker.expenseTracker.dto;

import com.example.expenseTracker.expenseTracker.model.Category;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ExpenseOutputDto {

    private Long id;
    private LocalDateTime expenseDate;
    private String description;
    private String amount;
    private Category category;
    private UserOutputDto user;
}
