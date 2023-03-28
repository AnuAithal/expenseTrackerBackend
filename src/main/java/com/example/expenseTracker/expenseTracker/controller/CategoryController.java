package com.example.expenseTracker.expenseTracker.controller;

import com.example.expenseTracker.expenseTracker.model.Category;
import com.example.expenseTracker.expenseTracker.repository.CategoryRepository;
import com.example.expenseTracker.expenseTracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;

    @GetMapping
    List<Category> getAllCategories(){

        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Long id){

        return categoryService.getCategoryById(id);
    }

    @PostMapping
    Category createCategory(@RequestBody Category category) throws URISyntaxException{
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    Category updateCategory(@PathVariable Long id,@RequestBody Category category){
        Category result = categoryService.updateCategory(id,category);
        //return ResponseEntity.ok().body(result);
        return result;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}















