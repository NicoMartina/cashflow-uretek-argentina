package com.cashflow.services;

import com.cashflow.models.Category;
import com.cashflow.models.Account;
import com.cashflow.models.Transaction;
import com.cashflow.repositories.CategoryRepository;
import com.cashflow.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AllocationService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public AllocationService(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Splits income into allocated transactions based on category percentages.
     * This is called AFTER the main account has received the income.
     * @param sourceTransaction The primary income transaction (e.g., Client Payment).
     */
    @Transactional
    public void allocateIncome(Transaction sourceTransaction) {

        // 1. Get all active categories with defined percentages
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            System.out.println("No budget categories defined. Skipping allocation.");
            return;
        }

        double totalAmount = sourceTransaction.getAmount();
        Account sourceAccount = sourceTransaction.getAccount();
        LocalDateTime allocationTime = LocalDateTime.now();

        for (Category category : categories) {

            Double percentage = category.getBudgetPercentage();
            if (percentage == null || percentage <= 0.0) continue; // Skip unallocated categories

            double allocatedAmount = totalAmount * percentage;

            // 2. Create a new "Allocation" transaction for each category
            Transaction allocatedTransaction = new Transaction();

            allocatedTransaction.setDescription("Allocation to " + category.getName());
            allocatedTransaction.setAmount(allocatedAmount);
            allocatedTransaction.setTransactionDate(allocationTime);

            // IMPORTANT: Link the internal transaction back to the original source account and mark as internal
            allocatedTransaction.setAccount(sourceAccount);

            // 3. Save the allocated transaction
            transactionRepository.save(allocatedTransaction);
        }
    }
}