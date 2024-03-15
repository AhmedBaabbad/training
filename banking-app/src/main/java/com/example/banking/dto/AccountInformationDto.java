package com.example.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformationDto {
    private String accountNumber;
    private String accountType;
    private LocalDate creationDate;
    private String accountStatus;
    private double balance;
}
