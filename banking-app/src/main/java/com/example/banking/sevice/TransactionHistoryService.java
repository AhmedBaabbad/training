package com.example.banking.sevice;

import com.example.banking.dto.TransactionHistoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionHistoryService {

    List<TransactionHistoryDto> getAllTransactionHistory();
    List<TransactionHistoryDto> getTransactionsType(Long accountId, String type);
}
