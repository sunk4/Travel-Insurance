package com.roman.Insurance.ageCategories;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "age_categories")
public class AgeCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Min age is required")
    @Min(value = 0, message = "Min age must be at least 0.")
    private Integer minAge;

    @Null(message = "Max age is required")
    @Min(value = 1, message = "Max age must be at least 1.")
    private Integer maxAge;


    @NotNull(message = "Price factor is required")
    @DecimalMin(value = "0.10", message = "Base price per day must be at least 0.10")
    private BigDecimal priceFactor;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}

