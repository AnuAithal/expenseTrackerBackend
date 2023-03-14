package com.example.expenseTracker.expenseTracker.repository;

import com.example.expenseTracker.expenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
