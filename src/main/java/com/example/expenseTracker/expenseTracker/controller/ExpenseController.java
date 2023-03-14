package com.example.expenseTracker.expenseTracker.controller;

import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.repository.CategoryRepository;
import com.example.expenseTracker.expenseTracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private ExpenseRepository expenseRepository;
    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/expenses")
    Collection<Expenses> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @GetMapping("/expenses/{id}")
    ResponseEntity<?> getExpense(@PathVariable Long id){
        Optional<Expenses> expenses = expenseRepository.findById(id);
        return expenses.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/expenses")
    ResponseEntity<Expenses> createExpense(@RequestBody Expenses expenses) throws URISyntaxException {
        Expenses result=expenseRepository.save(expenses);
        return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
    }

    @PutMapping("/expenses/{id}")
    ResponseEntity<Expenses> updateExpenses(@RequestBody Expenses expenses){
        Expenses result = expenseRepository.save(expenses);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/expenses/{id}")
    ResponseEntity<?> deleteExpense(@PathVariable Long id){
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
