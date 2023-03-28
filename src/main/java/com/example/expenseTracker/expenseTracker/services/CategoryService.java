package com.example.expenseTracker.expenseTracker.services;

import com.example.expenseTracker.expenseTracker.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(Category input);
    public Category getCategoryById(Long id);

    public List<Category> getAllCategories();

    public Category updateCategory(Long id,Category category);

    public Category deleteCategory(Long id);
}
