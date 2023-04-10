package com.example.expenseTracker.expenseTracker.repository;

import com.example.expenseTracker.expenseTracker.model.Expenses;
import com.example.expenseTracker.expenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {

    List<Expenses> findByUser(User user);
}
