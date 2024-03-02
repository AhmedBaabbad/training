package com.example.banking.sevice.impl;

import com.example.banking.dto.TransactionHistoryDto;
import com.example.banking.entity.TransactionHistory;
import com.example.banking.repository.TransactionHistoryRespository;
import com.example.banking.sevice.TransactionHistoryService;
import com.example.banking.utility.TransactionHistoryUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRespository transactionHistoryRespository;

    public TransactionHistoryServiceImpl(TransactionHistoryRespository transactionRepository) {
        this.transactionHistoryRespository = transactionRepository;
    }

    @Override
    public List<TransactionHistoryDto> getAllTransactionHistory() {

        List<TransactionHistory> transactionHistoryList = transactionHistoryRespository.findAll();

        // Check if transaction history list is empty
        if (transactionHistoryList.isEmpty()) {
            return Collections.emptyList();
        }

        // Map TransactionHistory objects to TransactionHistoryDto objects
        return transactionHistoryList.stream()
                .map(TransactionHistoryUtil::mapToTransactionHistoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionHistoryDto> getTransactionsType(Long accountId, String type) {
        List<TransactionHistory> transactions = transactionHistoryRespository.
                findByAccountIdAndType(accountId,type);
        return transactions.stream()
                .map(TransactionHistoryUtil::mapToTransactionHistoryDto)
                .collect(Collectors.toList());
    }

}
