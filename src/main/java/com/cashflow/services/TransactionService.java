package com.cashflow.services;

import com.cashflow.models.Account;
import com.cashflow.models.Transaction;
import com.cashflow.repositories.AccountRepository;
import com.cashflow.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TransactionService {

    // Constants for our specialized rules
    private static final double SAVINGS_MIN_BALANCE = 100.00;
    private static final double CHECKING_MAX_OVERDRAFT = -50.00;

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction){

        // 1. Retrieve the related Account (The Transaction object has the Account attached via the getter)
        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found. Cannot record transaction."));

        // 2. Calculate the hypothetical new balance
        double newBalance = account.getBalance() + transaction.getAmount();


        // 3. ENFORCE SPECIALIZED BUSINESS RULES (Core Logic Integration)

        // Check account type using name field (case-sensitive)
        boolean isSavings = account.getName().contains("Savings");
        boolean isChecking = account.getName().contains("Checkings");

        if (isSavings){
            // --- Savings Account Rule: Minimum Balance ---
            if (newBalance < SAVINGS_MIN_BALANCE){
                throw new IllegalArgumentException(
                        "Transaction failed: Savings account balance must remain  at least $" +SAVINGS_MIN_BALANCE
                );
            }
        }

        else if (isChecking){
            // --- Checking Account Rule: Overdraft Limit ---
            if(newBalance < CHECKING_MAX_OVERDRAFT){
                throw new IllegalArgumentException(
                        "Transaction failed: Checking account exceeds max overdraft limit of $" + CHECKING_MAX_OVERDRAFT
                );
            }
        }

        else if (newBalance < 0 ){
            // --- General Rule: No Overdraft for standard accounts ---
            throw new IllegalArgumentException("Transaction failed: Insufficient funds (Standard Accounts).");
        }

        // 4. If all checks pass, update and persist the Account and Transaction
        account.setBalance(newBalance);
        accountRepository.save(account);

        // 5. Finalize and save the Transaction
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    // Helper method to retrieve transactions for an account
    public List<Transaction> getTransactionsByAccount(Long accountId){
        return transactionRepository.findByAccount_Id(accountId);
    }
}