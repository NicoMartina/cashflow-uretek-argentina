package com.cashflow;

import org.springframework.stereotype.Service;

import java.util.Optional;

// 1. Add the Service annotation here
@Service
public class AccountService {

    private final AccountRepository accountRepository; // This is like the person who will be handling the job for us.

    // Constructor injection:  Spring gives us the worker automatically
    public  AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    //CREATE
    public Account save(Account account){
        if (account.getBalance() <= 0){
            throw new IllegalArgumentException("Initial balance must be positive");
        }
        return accountRepository.save(account);
    }

    //READ (by ID)
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    //READ (by NAME)
    public Optional<Account>  findByName(String name){
        return accountRepository.findByName(name);
    }

    //UPDATE (PUT/PATCH)
    public Account updateAccount(Long id, Account updatedAccount){
        // 1. Find the existing account or throw an error (404 Not Found)
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " not found."));

        // 2. Apply changes from the incoming data to the existing data
        if (updatedAccount.getName() != null) {
            existingAccount.setName(updatedAccount.getName());
        }

        // Only update the balance if a new balance is provided
        if (updatedAccount.getBalance() != 0) {
            existingAccount.setBalance(updatedAccount.getBalance());
        }

        // 3. Save the modified object (Repository runs the SQL UPDATE command)
        return  accountRepository.save(existingAccount);
    }


    //DELETE
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }


}
