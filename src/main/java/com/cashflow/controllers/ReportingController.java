package com.cashflow.controllers;

import com.cashflow.models.FinancialSummaryDTO;
import com.cashflow.services.ReportingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportingController {
    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    /**
     * Endpoint to get monthly financial summaries for a given year (defaulting to current year).
     * GET /api/v1/reports/monthly?year=2024
     */

    @GetMapping("/monthly")
    public List<FinancialSummaryDTO> getMonthlySummary(@RequestParam(defaultValue = "#{T(java.time.LocalDate).now().getYear()}") int year){
        return reportingService.getMonthlySummary(year);
    }

    /**
     * Endpoint to get the yearly financial summary for a given year (defaulting to current year).
     * GET /api/v1/reports/yearly?year=2024
     */

    @GetMapping("/yearly")
    public FinancialSummaryDTO getYearlySummary(@RequestParam(defaultValue = "#{T(java.time.LocalDate).now().getYear()}") int year){
        return reportingService.getYearlySummary(year);
    }

}
