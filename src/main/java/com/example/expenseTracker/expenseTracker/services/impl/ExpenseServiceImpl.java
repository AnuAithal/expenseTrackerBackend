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
import com.example.expenseTracker.expenseTracker.utils.CurrentUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {


    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public List<ExpenseOutputDto> getAllExpenses() {
        User user = CurrentUser.get();
        List<Expenses> expenses = expenseRepository.findByUser(user);
        List<ExpenseOutputDto> output = new ArrayList<>();
        for(Expenses expense : expenses){
            ExpenseOutputDto dto = modelMapper.map(expense, ExpenseOutputDto.class);
            if(expense.getCategory() != null){
                dto.setCategoryId(expense.getCategory().getId());
                dto.setCategoryName(expense.getCategory().getName());
            }
//            dto.setUserId(expense.getUser().getId());
            output.add(dto);
        }
        return output;
    }

    public ExpenseOutputDto getExpenseById(Long id) {
        Expenses expenses = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        ExpenseOutputDto map = modelMapper.map(expenses, ExpenseOutputDto.class);
        map.setCategoryId(expenses.getCategory().getId());
        map.setCategoryName(expenses.getCategory().getName());
//        map.setUserId(expenses.getUser().getId());
        return map;
    }

    public ExpenseOutputDto createExpense(ExpenseInputDto input) {

        Expenses expenses = modelMapper.map(input, Expenses.class);
        Category category = categoryRepository.findById(input.getCategoryId()).get();
        expenses.setCategory(category);
        expenses.setUser(CurrentUser.get());
        expenses = expenseRepository.save(expenses);
        ExpenseOutputDto output = modelMapper.map(expenses, ExpenseOutputDto.class);
        output.setCategoryId(expenses.getCategory().getId());
        output.setCategoryName(expenses.getCategory().getName());
//        output.setUserId(expenses.getUser().getId());
        return output;
    }

    public ExpenseOutputDto deleteExpense(Long id) {
        Expenses expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        expenseRepository.delete(expense);
        return modelMapper.map(expense, ExpenseOutputDto.class);
    }

    public ExpenseOutputDto updateExpense(Long id, ExpenseInputDto input) {

        Expenses expenses = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        modelMapper.map(input, expenses);
        Category category = categoryRepository.findById(input.getCategoryId()).get();
        expenses.setCategory(category);
//        User user =userRepository.findById(input.getUserId()).get();
//        expenses.setUser(user);
        expenses = expenseRepository.save(expenses);
        ExpenseOutputDto output = modelMapper.map(expenses, ExpenseOutputDto.class);
        output.setCategoryId(expenses.getCategory().getId());
        output.setCategoryName(expenses.getCategory().getName());
//        output.setUserId(expenses.getUser().getId());
        return output;

    }

    public ExpenseOutputDto getExpenseByUserId(Long id) {
//        Expenses expenses = expenseRepository.findByUserId(id).orElseThrow(() -> new RuntimeException());
//        ExpenseOutputDto map = modelMapper.map(expenses, ExpenseOutputDto.class);
//        map.setCategoryId(expenses.getCategory().getId());
//        map.setCategoryName(expenses.getCategory().getName());
////        map.setUserId(expenses.getUser().getId());
//        return map;
        return null;
    }



}
