package com.cashflow.controllers;

import com.cashflow.services.CategoryService;
import com.cashflow.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 1. CREATE (POST) / UPDATE (POST)
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }


    // 2. READ ALL (GET)
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    // 3. READ by ID (GET)
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. DELETE (DELETE)
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
