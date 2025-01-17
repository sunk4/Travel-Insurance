package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.enums.Continents;
import com.roman.Insurance.enums.InsuranceType;
import com.roman.Insurance.enums.StatusOfPayment;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "insurances")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Continent is required")
    private Continents continent;
    @NotBlank(message = "Country is required")
    private String country;
    @NotNull(message = "Trip length is required.")
    @Min(value = 1, message = "Trip length must be at least 1.")
    private int tripLength;
    @NotNull
    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent(message = "End date must be today or in the future.")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Insurance type is required")
    private InsuranceType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusOfPayment statusOfPayment;

    @NotNull
    private double totalPrice;

    private String urlInsurancePreview;
    private String urlInsurancePayed;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
