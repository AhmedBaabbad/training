package com.example.banking.repository;

import com.example.banking.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInformationRepository extends JpaRepository<AccountInformation,Long> {
}
