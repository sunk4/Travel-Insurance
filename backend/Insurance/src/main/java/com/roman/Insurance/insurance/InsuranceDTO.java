package com.roman.Insurance.insurance;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record InsuranceDTO(

        UUID id,

        @NotBlank(message = "Continent is required")
        String continent,

        @NotBlank(message = "Country is required")
        String country,

        @NotNull(message = "Trip length is required.")
        @Min(value = 1, message = "Trip length must be at least 1.")
        int tripLength,

        @NotNull
        @FutureOrPresent(message = "Start date must be today or in the future.")
        LocalDate startDate,

        @NotNull
        @FutureOrPresent(message = "End date must be today or in the future.")
        LocalDate endDate,

        @NotNull(message = "Insurance type is required")
        String type,

        @NotNull(message = "Status of payment is required")
        String statusOfPayment,

        UUID customerId
) {
}
