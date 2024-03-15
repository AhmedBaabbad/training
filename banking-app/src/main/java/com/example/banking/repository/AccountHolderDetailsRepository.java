package com.example.banking.repository;

import com.example.banking.entity.AccountHolderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderDetailsRepository extends JpaRepository<AccountHolderDetails, Long> {
}
