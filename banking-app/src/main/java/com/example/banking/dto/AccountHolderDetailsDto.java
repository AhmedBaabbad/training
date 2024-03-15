package com.example.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDetailsDto {

    private String fullName;
    private AddressDto address;
    private ContactInformationDto contactInformation;
    private AccountInformationDto accountInformation;
    private LocalDate dateOfBirth;
    private String identificationNumber;
}
