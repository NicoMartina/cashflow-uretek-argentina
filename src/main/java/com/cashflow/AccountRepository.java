package com.cashflow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This interface automatically provides all CRUD methods (save, findById, delete, etc.)
// <Entity Type, ID Type>
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Derived Query: Spring automatically generates the SQL for "SELECT * FROM ACCOUNT WHERE name = :name"
    Optional<Account> findByName(String name);
}