package com.roman.Insurance.insuranceType;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InsurancePriceCalculationRequest(

    LocalDate startDate,
    LocalDate endDate


) {
}
