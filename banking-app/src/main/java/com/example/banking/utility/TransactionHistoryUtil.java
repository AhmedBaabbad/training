package com.example.banking.utility;

import com.example.banking.dto.AccountDto;
import com.example.banking.dto.TransactionHistoryDto;
import com.example.banking.entity.Account;
import com.example.banking.entity.TransactionHistory;

public class TransactionHistoryUtil {

    public static TransactionHistory mapToTransactionHistory (TransactionHistoryDto transactionHistoryDto) {
        TransactionHistory transactionHistory= new TransactionHistory(
                transactionHistoryDto.getId(),
                transactionHistoryDto.getAccountId(),
                transactionHistoryDto.getAmount(),
                transactionHistoryDto.getTimestamp(),
                transactionHistoryDto.getType()
        );
        return transactionHistory;
    }

    public static TransactionHistoryDto mapToTransactionHistoryDto (TransactionHistory transactionHistory) {
        TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto(
                transactionHistory.getId(),
                transactionHistory.getAccountId(),
                transactionHistory.getAmount(),
                transactionHistory.getTimestamp(),
                transactionHistory.getType()
        );
        return transactionHistoryDto;
    }
}
