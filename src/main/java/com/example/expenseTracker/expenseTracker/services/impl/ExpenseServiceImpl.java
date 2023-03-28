package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.dto.ExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.CategoryRepository;
import com.example.expenseTracker.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {


    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;



    public List<ExpenseOutputDto> getAllExpenses(){
        List<Expenses> expenses=expenseRepository.findAll();
        Type listType = new TypeToken<List<ExpenseOutputDto>>(){}.getType();
        List<ExpenseOutputDto> dtoExpenseList = modelMapper.map(expenses, listType);
        return dtoExpenseList;
    }

    public ExpenseOutputDto getExpenseById(Long id){
        Expenses expenses=expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return modelMapper.map(expenses,ExpenseOutputDto.class);
    }

    public ExpenseOutputDto createExpense(ExpenseInputDto input){
//        Expenses expense = new Expenses();
//        expense.setExpenseDate(input.getExpenseDate());
//        expense.setAmount(input.getAmount());
//        expense.setDescription(input.getDescription());
//        Optional<Category> category = categoryRepository.findById(input.getCategoryId());
//        if(category.isEmpty())
//            throw new EntityNotFoundException("Category not found with given id");
//        expense.setCategory(category.get());
//
//        Optional<User> user = userRepository.findById(input.getUserId());
//        if(user.isEmpty())
//            throw new EntityNotFoundException("User not found with given id");
//        expense.setUser(user.get());
//        expense = expenseRepository.save(expense);
//
//
//        ExpenseOutputDto output = new ExpenseOutputDto();
//        output.setId(expense.getId());
//        output.setExpenseDate(expense.getExpenseDate());
//        output.setDescription(expense.getDescription());
//        output.setAmount(expense.getAmount());
//
//        return output;

        Expenses expenses=modelMapper.map(input, Expenses.class);
        expenses=expenseRepository.save(expenses);
        return modelMapper.map(expenses, ExpenseOutputDto.class);
    }

    public ExpenseOutputDto deleteExpense(Long id){
        Expenses expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        expenseRepository.delete(expense);
        return modelMapper.map(expense, ExpenseOutputDto.class);
    }

    public ExpenseOutputDto updateExpense(Long id, ExpenseInputDto input) {
        Expenses expense = expenseRepository.findById(id).orElseThrow(()->new RuntimeException());
        modelMapper.map(input, Expenses.class);
        expense=expenseRepository.save(expense);
        return modelMapper.map(expense, ExpenseOutputDto.class);
    }


}
