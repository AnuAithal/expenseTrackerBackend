package com.example.expenseTracker.expenseTracker.utils;

import com.example.expenseTracker.expenseTracker.model.User;

public class CurrentUser {
    private static final ThreadLocal<User> threadUser = new ThreadLocal<User>();

    public static void set(User user) {threadUser.set(user);}

    public static User get(){
        if(threadUser.get() == null){
            throw new RuntimeException("user does not exist currently");
        }
        return threadUser.get();
    }
}
