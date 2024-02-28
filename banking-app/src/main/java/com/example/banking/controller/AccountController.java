package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.sevice.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create a new account.
     *
     * @param accountDto The account details to create.
     * @return ResponseEntity<AccountDto> The created account DTO.
     */
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    /**
     * Retrieve an account by its ID.
     *
     * @param id The ID of the account to retrieve.
     * @return ResponseEntity<AccountDto> The account DTO if found, else 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Deposit money into an account.
     *
     * @param id      The ID of the account to deposit into.
     * @param request A map containing the amount to deposit.
     * @return ResponseEntity<AccountDto> The updated account DTO.
     */
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Withdraw money from an account.
     *
     * @param id      The ID of the account to withdraw from.
     * @param request A map containing the amount to withdraw.
     * @return ResponseEntity<AccountDto> The updated account DTO if successful, else 404 Not Found or 400 Bad Request.
     */
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Retrieve all accounts.
     *
     * @return ResponseEntity<List<AccountDto>> A list of all account DTOs.
     */
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Delete an account by its ID.
     *
     * @param id The ID of the account to delete.
     * @return ResponseEntity<String> A message indicating the success of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is successfully deleted");
    }
    /**
     * Update an account by its ID.
     *
     * @param id                 The ID of the account to update.
     * @param updatedAccountDto The updated account details.
     * @return ResponseEntity<AccountDto> The updated account DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto updatedAccountDto) {

        AccountDto updatedAccount = accountService.updateAccount(id, updatedAccountDto);
        return ResponseEntity.ok(updatedAccount);
    }
}
