package com.roman.Insurance.customer;

import com.roman.Insurance.insurance.Insurance;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import util.Encryption;

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

    @OneToMany(mappedBy = "customer")
    private List<Insurance> insurances;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void encryptFields() {
        try {
            if (firstName != null) {
                this.encryptedFistName = Encryption.encrypt(firstName);
            }
            if (lastName != null) {
                this.encryptedLastName = Encryption.encrypt(lastName);
            }
            if (email != null) {
                this.encryptedEmail = Encryption.encrypt(email);
            }
            if (phoneNumber != null) {
                this.encryptedPhoneNumber = Encryption.encrypt(phoneNumber);
            }
            if (address != null) {
                this.encryptedAddress = Encryption.encrypt(address);
            }
            if (city != null) {
                this.encryptedCity = Encryption.encrypt(city);
            }
            if (state != null) {
                this.encryptedState = Encryption.encrypt(state);
            }
            if (zipCode != null) {
                this.encryptedZipCode = Encryption.encrypt(zipCode);
            }
            if (personalIdentificationNumber != null) {
                this.encryptedPersonalIdentificationNumber = Encryption.encrypt(personalIdentificationNumber);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting fields", e);
        }
    }


    @PostLoad
    private void decryptFields() {
        try {
            if (encryptedFistName != null) {
                this.firstName = Encryption.decrypt(encryptedLastName);
            }
            if (encryptedLastName != null) {
                this.lastName = Encryption.decrypt(encryptedLastName);
            }
            if (encryptedPhoneNumber != null) {
                this.phoneNumber = Encryption.decrypt(encryptedPhoneNumber);
            }
            if (encryptedEmail != null) {
                this.email = Encryption.decrypt(encryptedEmail);
            }
            if (encryptedPhoneNumber != null) {
                this.phoneNumber = Encryption.decrypt(encryptedPhoneNumber);
            }
            if (encryptedAddress != null) {
                this.address = Encryption.decrypt(encryptedAddress);
            }
            if (encryptedCity != null) {
                this.city = Encryption.decrypt(encryptedCity);
            }
            if (encryptedState != null) {
                this.state = Encryption.decrypt(encryptedState);
            }
            if (encryptedZipCode != null) {
                this.zipCode = Encryption.decrypt(encryptedZipCode);
            }
            if (encryptedPersonalIdentificationNumber != null) {
                this.personalIdentificationNumber = Encryption.decrypt(encryptedPersonalIdentificationNumber);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error decrypting fields", e);
        }
    }

}
