package com.cashflow.repositories;

import com.cashflow.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This Repository manages the Budget Buckets
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // We need to look up categories by name easily
    Optional<Category> findByName(String name);

}
