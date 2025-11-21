package com.cashflow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Derived Query to find all transactions belonging to a specific account ID.
    List<Transaction> findByAccount_Id(Long accountId);
}
