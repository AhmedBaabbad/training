package com.example.banking.utility;

import com.example.banking.dto.*;
import com.example.banking.entity.*;
import com.example.banking.repository.AccountRespository;

import java.util.Random;

public class AccountUtil {

    private static AccountRespository accountRespository;

    public AccountUtil(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }



    public static AccountHolderDetails mapToAccount(AccountHolderDetailsDto accountHolderDetailsDTO) {

        AccountHolderDetails accountHolderDetails = new AccountHolderDetails(
                accountHolderDetailsDTO.getId(),
                accountHolderDetailsDTO.getFullName(),
                mapToAddress(accountHolderDetailsDTO.getAddress()),
                mapToContactInformation(accountHolderDetailsDTO.getContactInformation()),
                mapToAccountInformation(accountHolderDetailsDTO.getAccountInformation()),
                accountHolderDetailsDTO.getDateOfBirth(),
                accountHolderDetailsDTO.getIdentificationNumber()

        );
        return accountHolderDetails;
    }

    private static Address mapToAddress(AddressDto addressDTO) {
        // Map AddressDTO fields to Address fields
        Address address = new Address(
                addressDTO.getId(),
                addressDTO.getStreetAddress(),
                addressDTO.getCity(),
                addressDTO.getState(),
                addressDTO.getZipCode());

        return address;
    }

    private static AccountInformation mapToAccountInformation(AccountInformationDto accountInformationDTO) {

        AccountInformation accountInformation = new AccountInformation(
                // Assuming direct mapping, you can adjust this based on your actual DTOs
                accountInformationDTO.getId(),
                accountInformationDTO.getAccountNumber(),
                accountInformationDTO.getAccountType(),
                accountInformationDTO.getCreationDate(),
                accountInformationDTO.getAccountStatus(),
                accountInformationDTO.getBalance());

        return accountInformation;
    }

    private static ContactInformation mapToContactInformation(ContactInformationDto contactInformationDTO) {
        // Map ContactInformationDTO fields to ContactInformation fields
        ContactInformation contactInformation = new ContactInformation(
                contactInformationDTO.getId(),
                contactInformationDTO.getEmail(),
                contactInformationDTO.getPhoneNumber(),
                contactInformationDTO.getAlternatePhoneNumber());

        // Map other fields...
        return contactInformation;
    }

    public static AccountHolderDetailsDto mapToAccountDto(AccountHolderDetails accountHolderDetails) {

        AccountHolderDetailsDto accountHolderDetailsDto = new AccountHolderDetailsDto(
                accountHolderDetails.getId(),
                accountHolderDetails.getFullName(),
                mapToAddressDto(accountHolderDetails.getAddress()),
                mapToContactInformationDto(accountHolderDetails.getContactInformation()),
                mapToAccountInformationDto(accountHolderDetails.getAccountInformation()),
                accountHolderDetails.getDateOfBirth(),
                accountHolderDetails.getIdentificationNumber()

        );
        return accountHolderDetailsDto;
    }


    private static AddressDto mapToAddressDto(Address address) {
        // Map AddressDTO fields to Address fields
        AddressDto addressDTO = new AddressDto(
                address.getId(),
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZipCode());

        return addressDTO;
    }

    private static AccountInformationDto mapToAccountInformationDto(AccountInformation accountInformation) {

        AccountInformationDto accountInformationDto = new AccountInformationDto(
                // Assuming direct mapping, you can adjust this based on your actual DTOs
                accountInformation.getId(),
                accountInformation.getAccountNumber(),
                accountInformation.getAccountType(),
                accountInformation.getCreationAccountDate(),
                accountInformation.getAccountStatus(),
                accountInformation.getBalance());

        return accountInformationDto;
    }

    private static ContactInformationDto mapToContactInformationDto(ContactInformation contactInformation) {
        // Map ContactInformationDTO fields to ContactInformation fields
        ContactInformationDto contactInformationDto = new ContactInformationDto(
                contactInformation.getId(),
                contactInformation.getEmail(),
                contactInformation.getPhoneNumber(),
                contactInformation.getAlternatePhoneNumber());

        // Map other fields...
        return contactInformationDto;
    }

    public static <T, U> T mapToDto(U entity, Class<T> dtoClass) throws IllegalAccessException, InstantiationException {
        T dto = dtoClass.newInstance();

        for (java.lang.reflect.Field field : entity.getClass().getDeclaredFields()) {
            try {
                java.lang.reflect.Field dtoField = dtoClass.getDeclaredField(field.getName());
                field.setAccessible(true);
                dtoField.setAccessible(true);
                dtoField.set(dto, field.get(entity));
            } catch (NoSuchFieldException ignored) {
            }
        }

        return dto;
    }

    public static <T, U> U mapToEntity(T dto, Class<U> entityClass) throws IllegalAccessException, InstantiationException {
        U entity = entityClass.newInstance();

        for (java.lang.reflect.Field field : dto.getClass().getDeclaredFields()) {
            try {
                java.lang.reflect.Field entityField = entityClass.getDeclaredField(field.getName());
                field.setAccessible(true);
                entityField.setAccessible(true);
                entityField.set(entity, field.get(dto));
            } catch (NoSuchFieldException ignored) {
            }
        }

        return entity;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static String generateAccountNumber() {
        // Generate a random 9-digit number to fill the middle part
        Random random = new Random();
        String accountNumber;
        boolean isUnique = false;

        // Loop until a unique account number is generated
        do {
            long randomNumber = random.nextInt(900000000) + 100000000; // Random 9-digit number
            // Convert the get account number to a 9-digit string and pad it with leading zeros if necessary
            accountNumber = String.format("%09d", randomNumber);
            // Check if the generated account number exists in the database
            if (accountRespository == null || !accountRespository.findByAccountNumber(accountNumber)) {
                isUnique = true;
            }
        } while (!isUnique);

        // Concatenate "600" with the random 9-digit number to start any account number with 600
        return "600" + accountNumber;
    }

    public static String validateAccountType(String accountType) {
        if (accountType.equals(AccountConstants.ACCOUNT_CHECKING) ||
                accountType.equals(AccountConstants.ACCOUNT_SAVINGS) ||
                accountType.equals(AccountConstants.ACCOUNT_CREDIT_CARD)) {
            return accountType;
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }
}
