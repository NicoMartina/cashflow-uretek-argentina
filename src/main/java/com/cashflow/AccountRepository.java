package com.cashflow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Spring Boot takes care of the rest!

    Optional<Account> findByName(String name);
}