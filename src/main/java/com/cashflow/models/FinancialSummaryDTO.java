package com.cashflow.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialSummaryDTO {

    // For storing the time period (e.g., "January 2024", "Year 2024")
    private String period;
    // Total income recorded in this period
    private Double totalIncome;
    // Total expenditure recorded in this period
    private Double totalExpenditure;
    // Total difference (Income - Expenditure)
    private Double netRevenue;

}
