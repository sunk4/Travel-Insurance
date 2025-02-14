package com.roman.Insurance.insuranceType;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InsurancePriceCalculationRequest(
    UUID countryId,
    LocalDate startDate,
    LocalDate endDate,
    List<InsuranceTypeCalculationDetailDto> insuranceTypeCalculationDetails

) {
}
