package com.example.expenseTracker.expenseTracker.services.impl;

import com.example.expenseTracker.expenseTracker.dto.ExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.dto.ReccurExpenseInputDto;
import com.example.expenseTracker.expenseTracker.dto.ReccurExpenseOutputDto;
import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.model.ReccurExpense;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.repository.CategoryRepository;
import com.example.expenseTracker.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.expenseTracker.repository.ReccurExpenseRepo;
import com.example.expenseTracker.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.expenseTracker.services.ExpenseService;
import com.example.expenseTracker.expenseTracker.utils.CurrentUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final ReccurExpenseRepo reccurExpenseRepo;


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
            dto.setUserName(expense.getUser().getName());
            output.add(dto);
        }
        return output;
    }

    public ExpenseOutputDto getExpenseById(Long id) {
        Expenses expenses = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        ExpenseOutputDto map = modelMapper.map(expenses, ExpenseOutputDto.class);
        map.setCategoryId(expenses.getCategory().getId());
        map.setCategoryName(expenses.getCategory().getName());
        map.setUserName(expenses.getUser().getName());
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
        return output;
    }

    public ExpenseOutputDto deleteExpense(Long id) {
        Expenses expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException());
        expenseRepository.delete(expense);
        return modelMapper.map(expense, ExpenseOutputDto.class);
    }

//    public ReccurExpenseOutputDto deleteReccurExpense(Long id) {
//        ReccurExpense expense = reccurExpenseRepo.findById(id).orElseThrow(() -> new RuntimeException());
//        reccurExpenseRepo.delete(expense);
//        return modelMapper.map(expense, ReccurExpenseOutputDto.class);
//    }

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

    @Override
    public ReccurExpenseOutputDto createReccurExpense(ReccurExpenseInputDto input) {
        ReccurExpense expenses = new ReccurExpense();
// expenses.setExpenses(new Expenses());
        Expenses expenses1=new Expenses();
        expenses1.setExpenseDate(input.getExpenseDate());
        expenses1.setDescription(input.getDescription());
        expenses1.setAmount(input.getAmount());
        expenses1.setUser(CurrentUser.get());
        expenses.setExpenses(expenses1);
        Category category = categoryRepository.findById(input.getCategoryId()).get();
        expenses.getExpenses().setCategory(category);
// expenses.setId(CurrentUser.get().getId());
        expenses.setReccuringDate(input.getReccuringDate());
        expenses=reccurExpenseRepo.save(expenses);
        ReccurExpenseOutputDto output = modelMapper.map(expenses, ReccurExpenseOutputDto.class);
        output.setExpenseDate(expenses.getExpenses().getExpenseDate());
        output.setDescription(expenses.getExpenses().getDescription());
        output.setAmount(expenses.getExpenses().getAmount());
        output.setCategoryId(expenses.getExpenses().getCategory().getId());
        output.setCategoryName(expenses.getExpenses().getCategory().getName());
        output.setReccuringDate(input.getReccuringDate());

        return output;
    }

    @Override
    // @Scheduled(cron = "0 0 3 * * ?")
    @Scheduled(cron="*/5 * * * * * ")
    public void scheduleReccuringExpense() {
        System.out.println("cron is runnning");
        LocalDate dayOfMonth = LocalDate.now().withDayOfMonth(LocalDateTime.now().getDayOfMonth());
// List<ReccurExpense> byDateAndIsActive = reccurExpenseRepo.findByReccuringDateAndIsActive(String.valueOf(dayOfMonth), true);
        List<ReccurExpense> byDate = reccurExpenseRepo.findByReccuringDate(String.valueOf(dayOfMonth));
        for (ReccurExpense reccurExpense : byDate) {
            Expenses expenses = reccurExpense.getExpenses();
            expenses.setExpenseDate(LocalDateTime.now());
            expenseRepository.save(expenses);
        }
    }


}
