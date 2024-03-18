package com.example.banking.repository;

import com.example.banking.entity.AccountHolderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountHolderDetailsRepository extends JpaRepository<AccountHolderDetails, Long> {
/*
    @Query(value = "SELECT *\n" +
            "FROM account_holder_details ad\n" +
            "INNER JOIN account_information ai ON ad.account_information_id = ai.id\n" +
            "INNER JOIN contact_information ci ON ad.contact_information_id = ci.id\n" +
            "INNER JOIN address a ON ad.address_id = a.id\n" +
            "WHERE ai.account_number = ?1", nativeQuery = true) */

    @Query(value = "SELECT ad.id,ad.account_information_id,ad.address_id, ad.contact_information_id, " +
            "ad.full_name, ad.date_of_birth, ad.identification_number, " +
            "ai.account_number, ai.account_type, ai.creation_date, ai.account_status, ai.balance, " +
            "ci.email, ci.phone_number, ci.alternate_phone_number, " +
            "a.street_address, a.city, a.state, a.zip_code " +
            "FROM account_holder_details ad " +
            "INNER JOIN account_information ai ON ad.account_information_id = ai.id " +
            "INNER JOIN contact_information ci ON ad.contact_information_id = ci.id " +
            "INNER JOIN address a ON ad.address_id = a.id " +
            "WHERE ai.account_number = ?1", nativeQuery = true)

    Optional <AccountHolderDetails> findByAccountNumber(String accountNumber);


}
