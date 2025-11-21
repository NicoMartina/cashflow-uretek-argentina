package com.cashflow.services;

import com.cashflow.models.Category;
import com.cashflow.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// The Service layer enforces business rules and manages the repository
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //CREATE / UPDATE
    public Category save(Category category){
        // Business Rule: Percentage must be between 0 and 1 (0% to 100%)
        if (category.getBudgetPercentage() == null ||
            category.getBudgetPercentage() < 0.0 ||
            category.getBudgetPercentage() > 1.0) {

            throw new IllegalArgumentException("Budget percentage  must be 0.0 and 1.0 (e.g., 0.15 for 15%).");
        }

        return categoryRepository.save(category);
    }


    // READ ALL
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // READ BY ID
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    // DELETE
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
