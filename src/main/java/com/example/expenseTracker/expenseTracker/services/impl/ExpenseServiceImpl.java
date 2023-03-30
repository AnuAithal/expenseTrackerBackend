package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.dto.ExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.repository.CategoryRepository;
import com.example.expenseTracker.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.ExpenseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
        List<Expenses> expenses = expenseRepository.findAll();
        List<ExpenseOutputDto> output = new ArrayList<>();
        for(Expenses expense : expenses){
            ExpenseOutputDto dto = modelMapper.map(expense, ExpenseOutputDto.class);
            if(expense.getCategory() != null){
                dto.setCategoryId(expense.getCategory().getId());
                dto.setCategoryName(expense.getCategory().getName());
            }

            output.add(dto);
        }
        return output;
    }

    public ExpenseOutputDto getExpenseById(Long id) {
        Expenses expenses = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        ExpenseOutputDto map = modelMapper.map(expenses, ExpenseOutputDto.class);
        map.setCategoryId(expenses.getCategory().getId());
        map.setCategoryName(expenses.getCategory().getName());
        return map;
    }

    public ExpenseOutputDto createExpense(ExpenseInputDto input) {
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

        Expenses expenses = modelMapper.map(input, Expenses.class);
        Category category = categoryRepository.findById(input.getCategoryId()).get();
        expenses.setCategory(category);
        expenses = expenseRepository.save(expenses);
        ExpenseOutputDto output = modelMapper.map(expenses, ExpenseOutputDto.class);
        output.setCategoryId(expenses.getCategory().getId());
        output.setCategoryName(expenses.getCategory().getName());
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
        expenses = expenseRepository.save(expenses);
        ExpenseOutputDto output = modelMapper.map(expenses, ExpenseOutputDto.class);
        output.setCategoryId(expenses.getCategory().getId());
        output.setCategoryName(expenses.getCategory().getName());
        return output;

    }


}
