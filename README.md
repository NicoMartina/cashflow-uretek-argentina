CashFlow Uretek Argentina: Financial Allocation and Reporting System

🚀 Project Overview

This is a modern, enterprise-grade RESTful API designed to manage the financial operations of Uretek Argentina. The core functionality is to provide real-time Revenue Allocation across predefined budget categories (e.g., Marketing, Payroll) and generate structured, aggregated Financial Reporting (monthly/yearly totals).

This system was architected, developed, and deployed by me (Nicolás Martina) as the company's Lead Software Engineer, establishing the entire IT infrastructure and development workflow.

🛠️ Technology Stack

Core Language: Java 17+

Framework: Spring Boot 3.x

Persistence: Spring Data JPA (Hibernate)

Database: PostgreSQL (Assumed)

Utility: Lombok (For boilerplate reduction)

Build Tool: Maven

🏛️ Architectural Design (Layered Structure)

The application uses the standard Model-View-Controller (MVC) pattern adapted for APIs, emphasizing Separation of Concerns.

Controller (controllers): Handles all HTTP requests (GET, POST, PUT, DELETE) and responses (JSON). It only communicates with the Service layer.

Service (services): The Business Logic Layer. Enforces all custom rules, performs complex calculations, and manages transactions (@Transactional).

Includes: TransactionService, AllocationService, ReportingService.

Repository (repository): The Data Access Layer. Extends JpaRepository to automatically translate Java method calls into SQL queries (CRUD).

Model (model): The Data Layer (JPA Entities). Represents the database tables (Account, Transaction, Category).

✨ Key Business Features

This application goes beyond simple CRUD to solve complex business needs:

1. Automated Financial Allocation (The Core Feature)

The AllocationService automatically splits incoming funds (amount > 0) into internal budget transactions based on percentages defined in the Category entity.

2. Transactional Integrity

The TransactionService uses the @Transactional annotation to ensure that any failure during a transaction (e.g., a rule violation) triggers a complete database rollback.

3. Specialized Account Rules

The system enforces OOP principles by applying different rules based on the account name:

Savings Account: Enforces a minimum balance (e.g., $100.00).

Checking Account: Allows a limited overdraft (e.g., up to $-\$50.00$).

4. Advanced Reporting

The ReportingService aggregates transaction data using Java Stream API and custom repository queries (findByTransactionDateBetween) to provide structured financial analysis (Gross Income, Total Expenditure, Net Revenue).

🔑 Running the Application

Prerequisites

Java 17+ SDK

Maven 3.x

PostgreSQL Database (or configure application.properties for H2/MySQL)

Steps

Clone the Repository:

git clone [https://github.com/NicoMartina/cashflow-uretek-argentina.git](https://github.com/NicoMartina/cashflow-uretek-argentina.git)
cd cashflow-uretek-argentina


Build and Run (Maven):

./mvnw clean install
./mvnw spring-boot:run


🌐 Key API Endpoints (For Testing with Postman)

The application runs on http://localhost:8080 (default Spring Boot port).

Purpose

Method

Endpoint

Body/Notes

Create Account

POST

/api/v1/account

{ "name": "Checking Account", "balance": 500.00 }

Create Category

POST

/api/v1/categories

{ "name": "Marketing", "budgetPercentage": 0.15 }

Record Income (Triggers Allocation)

POST

/api/v1/transactions

{ "amount": 1000.00, "description": "Client Payment", "account": { "id": 1 } }

Get Monthly Report

GET

/api/v1/reports/monthly?year=2024

Shows income/expenditure totals by month.

Delete Account

DELETE

/api/v1/account/{id}

E.g., /api/v1/account/1
