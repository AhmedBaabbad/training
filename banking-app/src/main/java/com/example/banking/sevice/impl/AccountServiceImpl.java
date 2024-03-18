package com.example.banking.sevice.impl;

import com.example.banking.dto.*;
import com.example.banking.entity.*;
import com.example.banking.repository.AccountHolderDetailsRepository;
import com.example.banking.repository.AccountRespository;
import com.example.banking.repository.TransactionHistoryRespository;
import com.example.banking.sevice.AccountService;
import com.example.banking.utility.AccountUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRespository accountRespository;
    private final AccountHolderDetailsRepository accountHolderDetailsRepository;
    private final TransactionHistoryRespository transactionHistoryRespository;

    public AccountServiceImpl(AccountRespository accountRespository,
                              AccountHolderDetailsRepository accountHolderDetailsRepository,
                              TransactionHistoryRespository transactionHistoryRespository
    ) {
        this.accountRespository = accountRespository;
        this.transactionHistoryRespository = transactionHistoryRespository;
        this.accountHolderDetailsRepository = accountHolderDetailsRepository;
    }

   /* @Override
    public AccountDto createAccount(AccountDto accountDto) throws IllegalAccessException, InstantiationException {
        // Generate account number
        String accountNumber= AccountUtil.generateAccountNumber();
        Account account = AccountUtil.mapToAccount(accountDto);
        account.setAccountNumber(accountNumber);
        Account createdAccount = accountRespository.save(account);
       // return AccountUtil.mapToAccountDto(createdAccount);
        return AccountUtil.mapToDto(createdAccount,AccountDto.class);
    } */

/*    @Transactional
    @Override
    public AccountHolderDetails createAccount(AccountHolderDetailsDto accountHolderDetailsDto) throws IllegalAccessException, InstantiationException {
        // Generate account number
        String accountNumber = AccountUtil.generateAccountNumber();
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Address address = new Address();
        address.setStreetAddress(accountHolderDetailsDto.getAddress().getStreetAddress());
        address.setCity(accountHolderDetailsDto.getAddress().getCity());
        address.setState(accountHolderDetailsDto.getAddress().getState());
        address.setZipCode(accountHolderDetailsDto.getAddress().getZipCode());

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(accountHolderDetailsDto.getContactInformation().getEmail());
        contactInformation.setPhoneNumber(accountHolderDetailsDto.getContactInformation().getPhoneNumber());
        contactInformation.setAlternatePhoneNumber(accountHolderDetailsDto.getContactInformation().getAlternatePhoneNumber());

        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setAccountNumber(accountNumber);
        accountInformation.setAccountType(AccountUtil.
                validateAccountType(accountHolderDetailsDto.getAccountInformation().getAccountType()));
        accountInformation.setAccountStatus(AccountConstants.ACCOUNT_STATUS_ACTIVE);
        accountInformation.setCreationAccountDate(formattedDateTime);
        accountInformation.setBalance(accountHolderDetailsDto.getAccountInformation().getBalance());

        // Create AccountHolderDetails entity
        AccountHolderDetails accountHolderDetails = new AccountHolderDetails();

        accountHolderDetails.setFullName(accountHolderDetailsDto.getFullName());
        accountHolderDetails.setAddress(address);
        accountHolderDetails.setContactInformation(contactInformation);
        accountHolderDetails.setAccountInformation(accountInformation);
        accountHolderDetails.setDateOfBirth(accountHolderDetailsDto.getDateOfBirth());
        accountHolderDetails.setIdentificationNumber(accountHolderDetailsDto.getIdentificationNumber());
        // Save AccountHolderDetails entity
        return accountHolderDetailsRepository.save(accountHolderDetails);
    } */

    @Transactional
    @Override
    public AccountHolderDetailsDto createAccount(AccountHolderDetailsDto accountHolderDetailsDto) throws IllegalAccessException, InstantiationException {
        // Generate account number
        String accountNumber = AccountUtil.generateAccountNumber();
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // Convert DTOs to entities
        Address address = AccountUtil.mapToEntity(accountHolderDetailsDto.getAddress(), Address.class);
        ContactInformation contactInformation = AccountUtil.mapToEntity(accountHolderDetailsDto.getContactInformation(), ContactInformation.class);
        AccountInformation accountInformation = AccountUtil.mapToEntity(accountHolderDetailsDto.getAccountInformation(), AccountInformation.class);

        // Populate additional fields
        accountInformation.setAccountNumber(accountNumber);
        accountInformation.setAccountType(AccountUtil.validateAccountType(accountHolderDetailsDto.getAccountInformation().getAccountType()));
        accountInformation.setAccountStatus(AccountConstants.ACCOUNT_STATUS_ACTIVE);
        accountInformation.setCreationAccountDate(formattedDateTime);

        // Create AccountHolderDetails entity
        AccountHolderDetails accountHolderDetails = new AccountHolderDetails();

        accountHolderDetails.setFullName(accountHolderDetailsDto.getFullName());
        accountHolderDetails.setAddress(address);
        accountHolderDetails.setContactInformation(contactInformation);
        accountHolderDetails.setAccountInformation(accountInformation);
        accountHolderDetails.setDateOfBirth(accountHolderDetailsDto.getDateOfBirth());
        accountHolderDetails.setIdentificationNumber(accountHolderDetailsDto.getIdentificationNumber());

        // Save AccountHolderDetails entity
        AccountHolderDetails savedAccountHolderDetails = accountHolderDetailsRepository.save(accountHolderDetails);

        // Convert saved entity to DTO
        AccountHolderDetailsDto savedAccountHolderDetailsDto = AccountUtil.mapToAccountDto(savedAccountHolderDetails);

        return savedAccountHolderDetailsDto;
    }

    @Override
    public AccountHolderDetailsDto getAllByAccountNumber(String accountNumber) {

        AccountHolderDetails accountByAccountNumber = accountHolderDetailsRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException(" Account number does not exist"));
        return AccountUtil.mapToAccountDto(accountByAccountNumber);
    }


    @Override
    public AccountDto getAccountById(Long id) {
        Account accountById = accountRespository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(" Account does not exist"));
        return AccountUtil.mapToAccountDto(accountById);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(" Account does not exist"));

        return processTransaction(id, amount, AccountConstants.TRANSACTION_DEPOSIT);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(" Account does not exist"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficent balance");
        }
        return processTransaction(id, amount, AccountConstants.TRANSACTION_WITHDRAWAL);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRespository.findAll();
        return accountList.stream()
                .map(accounts -> AccountUtil.mapToAccountDto(accounts))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(" Account does not exist"));
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

    @Override
    public AccountDto transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = accountRespository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From Account does not exist"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        Account toAccount = accountRespository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To Account does not exist"));

        processTransaction(fromAccount.getId(), amount, AccountConstants.TRANSACTION_WITHDRAWAL);
        processTransaction(toAccount.getId(), amount, AccountConstants.TRANSACTION_DEPOSIT);

        return getAccountById(fromAccount.getId());
    }

    private AccountDto processTransaction(Long id, double amount, String transactionType) {
        Account account = accountRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        double newBalance;
        if (AccountConstants.TRANSACTION_DEPOSIT.equals(transactionType)) {
            newBalance = account.getBalance() + amount;
        } else {
            newBalance = account.getBalance() - amount;
        }
        account.setBalance(newBalance);
        Account updatedAccount = accountRespository.save(account);

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAccountId(id);
        transactionHistory.setAmount(amount);
        transactionHistory.setTimestamp(LocalDateTime.now());
        transactionHistory.setType(transactionType);
        transactionHistoryRespository.save(transactionHistory);

        return AccountUtil.mapToAccountDto(updatedAccount);

    }

}
