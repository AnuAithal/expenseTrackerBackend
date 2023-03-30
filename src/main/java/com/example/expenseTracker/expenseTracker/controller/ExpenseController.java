package com.example.expenseTracker.expenseTracker.controller;

import com.example.expenseTracker.expenseTracker.dto.ExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.expenseTracker.services.ExpenseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin
@Data
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseService expenseService;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @GetMapping
    public List<ExpenseOutputDto> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseOutputDto getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    public ExpenseOutputDto createExpense(@RequestBody ExpenseInputDto input){
        return expenseService.createExpense(input);
    }

    @DeleteMapping("/{id}")
    public ExpenseOutputDto deleteExpense(@PathVariable Long id){
        return expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public ExpenseOutputDto updateExpense(@PathVariable Long id, @RequestBody ExpenseInputDto input){
        return expenseService.updateExpense(id, input);
    }



}
