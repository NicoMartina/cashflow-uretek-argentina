package com.cashflow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    // 1. PRIMARY KEY SETUP: ID and automatic generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 2. Data Fields
    private Double amount;
    private String description;

    // We'll use LocalDateTime for proper date and time storage
    private LocalDateTime transactionDate;


    // 3. FOREIGN KEY RELATIONSHIP: Many Transactions belong to One Account
    // This is the "many" side of the relationship (many transactions belong to one account)
    @ManyToOne
    @JoinColumn(name = "account_id") // This specifies the foreign key column name in the database
    private Account account;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
