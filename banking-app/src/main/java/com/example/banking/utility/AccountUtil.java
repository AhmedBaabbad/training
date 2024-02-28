package com.example.banking.utility;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;

public class AccountUtil {

    public static Account mapToAccount (AccountDto accountDto) {
        Account account= new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto (Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
