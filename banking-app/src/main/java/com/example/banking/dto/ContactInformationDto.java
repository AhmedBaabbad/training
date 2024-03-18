package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformationDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String alternatePhoneNumber;
}
