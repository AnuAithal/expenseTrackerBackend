package com.example.expenseTracker.expenseTracker.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

//    @Value("${firebase.file}")
    private String FIREBASE_CONFIG_FILE;
}
