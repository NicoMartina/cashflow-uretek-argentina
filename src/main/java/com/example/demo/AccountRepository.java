package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Account; // Import your entity

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Spring Boot takes care of the rest!
}