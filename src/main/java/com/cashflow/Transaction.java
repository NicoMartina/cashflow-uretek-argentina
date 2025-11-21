package com.cashflow;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
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
    private LocalDate transactionDate;


    // 3. FOREIGN KEY RELATIONSHIP: Many Transactions belong to One Account
    // This is the "many" side of the relationship (many transactions belong to one account)
    @ManyToOne
    @JoinColumn(name = "account_id") // This specifies the foreign key column name in the database
    private Account account;


}
