package com.example.expenseTracker.expenseTracker.repository;

import com.example.expenseTracker.expenseTracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expenses, Long> {
}
