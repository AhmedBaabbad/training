package com.example.banking.sevice;

import com.example.banking.dto.AccountDto;
import com.example.banking.dto.AccountHolderDetailsDto;
import com.example.banking.entity.AccountHolderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    // AccountDto createAccount (AccountDto accountDto) throws IllegalAccessException, InstantiationException;

    AccountHolderDetailsDto createAccount(AccountHolderDetailsDto accountHolderDetailsDto) throws IllegalAccessException, InstantiationException;

    AccountHolderDetailsDto getAllByAccountNumber(String accountNumber) ;

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    AccountDto updateAccount(Long id, AccountDto updatedAccountDto);

    AccountDto transfer(Long fromAccountId, Long toAccountId, double amount);

}
