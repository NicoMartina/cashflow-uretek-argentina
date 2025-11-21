package com.cashflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cashflow.controllers", "com.cashflow.services", "com.cashflow.repositories", "com.cashflow.models"})
public class CashFlowUretekArgentinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashFlowUretekArgentinaApplication.class, args);
	}

}
