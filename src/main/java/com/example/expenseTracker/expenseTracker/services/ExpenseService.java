package com.example.expenseTracker.expenseTracker.services;

import com.example.expenseTracker.expenseTracker.dto.ExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.dto.ReccurExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ReccurExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.model.Expenses;

import java.util.List;

public interface ExpenseService {

    public List<ExpenseOutputDto> getAllExpenses();

    public ExpenseOutputDto getExpenseById(Long id);

    public ExpenseOutputDto createExpense(ExpenseInputDto input);

    public ExpenseOutputDto deleteExpense(Long id);
    
    public ExpenseOutputDto updateExpense(Long id,ExpenseInputDto input);

    public ReccurExpenseOutputDto createReccurExpense(ReccurExpenseInputDto input);

    public void scheduleReccuringExpense();

}
