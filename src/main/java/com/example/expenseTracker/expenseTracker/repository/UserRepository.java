package com.example.expenseTracker.expenseTracker.repository;

import com.example.expenseTracker.expenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirebaseId(String uid);
}
