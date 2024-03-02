package com.example.banking.controller;

import com.example.banking.dto.TransactionHistoryDto;
import com.example.banking.sevice.TransactionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transcations")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    /**
     * Retrieve transaction history based on accountId and type.
     *
     * @param accountId The ID of the account for which transactions are requested.
     * @param type      The type of transactions (e.g., DEPOSIT, WITHDRAWAL).
     * @return          List of transaction history DTOs matching the criteria.
     */
    @GetMapping()
    public List<TransactionHistoryDto> getTransactionsType(
            @RequestParam("accountId") Long accountId,
            @RequestParam("type") String type) {

        return transactionHistoryService.getTransactionsType(accountId, type);
    }
    /**
     * Retrieve all transaction history records.
     *
     * @return List of all transaction history DTOs.
     */
    @GetMapping("/list")
    public List<TransactionHistoryDto> getAllTransactions(){
        return transactionHistoryService.getAllTransactionHistory();
    }
}
