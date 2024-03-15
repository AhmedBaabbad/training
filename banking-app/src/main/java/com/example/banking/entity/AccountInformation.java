package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "creation_date")
    private String creationAccountDate;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "balance")
    private double balance;
}
