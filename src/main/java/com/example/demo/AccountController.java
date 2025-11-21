package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// The @RestController annotation marks this class as a request handler
@RestController
// @RequestMapping sets the base URL path for all endpoints in this controller
@RequestMapping(path = "api/v1/account")
public class AccountController {
    private final AccountService accountService;

    // Dependency Injection: Spring automatically provides the AccountService instance
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    // 1. READ (GET) - Endpoint: GET /api/v1/account/{accountId}
    // @GetMapping maps HTTP GET requests to this method
    // @PathVariable extracts the ID from the URL path
    @GetMapping
    public ResponseEntity<Account> getAccountById(@RequestBody Long accountId){
        // Use the Service layer to find the entity
        Optional<Account> account = accountService.findById(accountId);

        // Functional code using Optional to handle presence or absence of the account:
        // If present, return 200 OK with the account body.
        // If absent, return 404 Not Found.
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 2. CREATE (POST) - Endpoint: POST /api/v1/account
    // @PostMapping maps HTTP POST requests to this method
    // @RequestBody binds the JSON data sent in the request body to the Account object
    @PostMapping
    public Account createAccount(@RequestBody Account newAccount){
        // Use the Service layer to save the new account (which results in an SQL INSERT)
        // This returns the saved entity, including the database-generated ID.
        return accountService.save(newAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteById(accountId);
        return ResponseEntity.noContent().build();
    }


}
