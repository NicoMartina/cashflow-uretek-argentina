package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Account; // Import your entity

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Spring Boot takes care of the rest!

    Optional<Account> findByName(String name);
}