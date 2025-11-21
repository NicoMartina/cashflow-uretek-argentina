package com.cashflow.services;

import com.cashflow.models.FinancialSummaryDTO;
import com.cashflow.models.Transaction;
import com.cashflow.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {
    private final TransactionRepository transactionRepository;

    public ReportingService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<FinancialSummaryDTO> getMonthlySummary(int year) {
        LocalDateTime yearStart = LocalDate.of(year,1,1).atStartOfDay();
        LocalDateTime yearEnd = LocalDate.of(year,12,31).atTime(23,59,59);

        // Uses the derived query we added earlier
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(yearStart, yearEnd);

        // Group transactions by month and calculate totals
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionDate().getMonth(),
                        Collectors.toList()
                ))
                .entrySet().stream()
                .map(entry -> {
                    String period = entry.getKey().toString() + " " + year;
                    return calculateSummary(period, entry.getValue());
                })
                .collect(Collectors.toList());
    }

    public FinancialSummaryDTO getYearlySummary(int year){
        LocalDateTime yearStart = LocalDate.of(year, 1,1).atStartOfDay();
        LocalDateTime yearEnd = LocalDate.of(year,12,31).atTime(23,59,59);

        List<Transaction>transactions = transactionRepository.findByTransactionDateBetween(yearStart,yearEnd);

        String period = "Year " + year;
        return calculateSummary(period, transactions);
    }

    public FinancialSummaryDTO calculateSummary(String period, List<Transaction> transactions) {
        // Calculate total income (positive amounts)
        double income = transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        // Calculate total expenditure (negative amounts)
        double expenditure = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        return new FinancialSummaryDTO(
                period,
                income,
                Math.abs(expenditure),
                income + expenditure
        );
    }
}
