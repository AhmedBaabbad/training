package com.example.banking.utility;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRespository;

import java.util.Random;

public class AccountUtil {

    private static AccountRespository accountRespository;

    public AccountUtil(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    public static Account mapToAccount (AccountDto accountDto) {
        Account account= new Account(
                accountDto.getId(),
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto (Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }

    public static String generateAccountNumber() {
        // Generate a random 9-digit number to fill the middle part
        Random random = new Random();
        String accountNumber;
        boolean isUnique =false;

        // Loop until a unique account number is generated
        do {
        long randomNumber  = random.nextInt(900000000) + 100000000; // Random 9-digit number
            // Convert the get account number to a 9-digit string and pad it with leading zeros if necessary
         accountNumber = String.format("%09d", randomNumber );
        // Check if the generated account number exists in the database
            if (accountRespository == null || !accountRespository.findByAccountNumber(accountNumber)) {
                isUnique = true;
            }
        } while (!isUnique);

        // Concatenate "600" with the random 9-digit number to start any account number with 600 
        return "600"+ accountNumber;
    }
}
