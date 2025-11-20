package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Optional;

// 1. Add the Service annotation here
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    // 2. Define the Constructor for Dependency Injection (DI)
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account){
        accountRepository.save(account);
        return account;
    }

}
