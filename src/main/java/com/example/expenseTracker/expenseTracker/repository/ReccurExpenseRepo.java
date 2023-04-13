package com.example.expenseTracker.expenseTracker.repository;

import com.example.expenseTracker.expenseTracker.model.ReccurExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReccurExpenseRepo extends JpaRepository<ReccurExpense, Long> {
    List<ReccurExpense> findByReccuringDate(String date);
}
