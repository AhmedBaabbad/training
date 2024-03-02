package com.example.banking.repository;

import com.example.banking.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionHistoryRespository extends JpaRepository<TransactionHistory,Long> {

   /* @Query("SELECT t.amount, t.timestamp, t.type " +
            "FROM transaction_history t " +
            "WHERE t.account_Id = :accountId " +
            "AND t.type = :transactionType")
    List<TransactionHistory> findByAccountIdAndTransactionType(Long accountId, String transactionType); */

    List<TransactionHistory> findByAccountIdAndType(Long accountId, String type);
}
