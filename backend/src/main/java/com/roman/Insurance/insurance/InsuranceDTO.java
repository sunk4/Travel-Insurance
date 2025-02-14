package com.roman.Insurance.insurance;

import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.enums.InsuranceType;
import com.roman.Insurance.enums.StatusOfPayment;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuranceDTO(

        UUID id,

        @NotNull(message = "Country is required")
        CountryDto country,

        int tripLength,

        @NotNull
        @FutureOrPresent(message = "Start date must be today or in the future.")
        LocalDate startDate,

        @NotNull
        @FutureOrPresent(message = "End date must be today or in the future.")
        LocalDate endDate,
        @NotNull(message = "Insurance type is required")
        InsuranceType type,

        StatusOfPayment statusOfPayment,

        String urlInsurancePreview,
        String urlInsurancePayed,
        double totalPrice,


        UUID customerId,

        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
