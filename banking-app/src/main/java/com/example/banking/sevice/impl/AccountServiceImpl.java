package com.example.banking.sevice.impl;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRespository;
import com.example.banking.sevice.AccountService;
import com.example.banking.utility.AccountUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRespository accountRespository ;

    public AccountServiceImpl(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountUtil.mapToAccount(accountDto);
        Account createdAccount= accountRespository.save(account);
        return AccountUtil.mapToAccountDto(createdAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account accountById= accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException(" Account does not exist"));
        return AccountUtil.mapToAccountDto(accountById);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException(" Account does not exist"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account depositAmount=accountRespository.save(account);
        return AccountUtil.mapToAccountDto(depositAmount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException(" Account does not exist"));
        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficent balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account withdrawAmount=accountRespository.save(account);
        return AccountUtil.mapToAccountDto(withdrawAmount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList= accountRespository.findAll();
        return accountList.stream()
                .map(accounts-> AccountUtil.mapToAccountDto(accounts))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException(" Account does not exist"));
        accountRespository.deleteById(id);
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto updatedAccountDto) {
        Account existingAccount = accountRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        // Update account details with the new values
        existingAccount.setAccountHolderName(updatedAccountDto.getAccountHolderName());

        // Save the updated account
        Account updatedAccount = accountRespository.save(existingAccount);

        // Map the updated account to DTO and return
        return AccountUtil.mapToAccountDto(updatedAccount);

}
}
