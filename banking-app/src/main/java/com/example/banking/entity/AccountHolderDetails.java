package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="account_holder_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHolderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    private ContactInformation contactInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountInformation_id", referencedColumnName = "id")
    private AccountInformation accountInformation;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "identification_number", unique = true)
    private String identificationNumber;

}
