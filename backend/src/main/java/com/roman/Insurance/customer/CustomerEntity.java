package com.roman.Insurance.customer;

import com.roman.Insurance.encryption.EncryptionService;
import com.roman.Insurance.insurance.InsuranceEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "customers")
public class CustomerEntity {

    @Transient
    @Autowired
    private transient EncryptionService encryptionService;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String encryptedFistName;

    private String encryptedLastName;

    private String encryptedEmail;

    private String encryptedPhoneNumber;

    private String encryptedAddress;

    private String encryptedCity;

    private String encryptedState;

    private String encryptedZipCode;

    private String encryptedPersonalIdentificationNumber;

    @Transient
    @NotBlank(message = "First name is required")
    private String firstName;
    @Transient
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Transient
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    @Transient
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @Transient
    @NotBlank(message = "Address is required")
    private String address;
    @Transient
    @NotBlank(message = "City is required")
    private String city;
    @Transient
    @NotBlank(message = "State is required")
    private String state;
    @Transient
    @NotBlank(message = "Zip code is required")
    private String zipCode;
    @Transient
    @NotBlank(message = "Personal identification number is required")
    private String personalIdentificationNumber;
    @NotNull(message = "Age is required")
    private int age;

    @OneToMany(mappedBy = "customer")
    private List<InsuranceEntity> insurances;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
