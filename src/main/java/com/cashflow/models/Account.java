package com.cashflow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    //private fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private double balance;


    // --- CONSTRUCTORS (JPA requirement) ---

    // JPA requires a no-argument constructor
    public Account(){}


    // Constructor used for creating new objects in memory
    public Account(String name, double balance){
        this.name = name;

    }

    //GETTERS AND SETTERS

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    // 4. Public Business Methods (Mutators/Actions)

    /**
     * Adds money to the account balance.
     * @param amount The amount to deposit.
     * @return true if deposit was successful.
     */

    public boolean deposit(double amount){
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    /**
     * Attempts to withdraw money from the account.
     * @param amount The amount to withdraw.
     * @return true if withdrawal was successful (balance was sufficient).
     */

    public boolean withdraw( double amount){
        if (amount > 0 && this.balance >= amount){
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
