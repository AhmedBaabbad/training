package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDto {

    private Long id;
    private Long accountId;
    private double amount;
    private LocalDateTime timestamp;
    private String type;
}
