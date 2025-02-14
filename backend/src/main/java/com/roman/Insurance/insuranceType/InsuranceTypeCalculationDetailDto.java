package com.roman.Insurance.insuranceType;

import java.util.UUID;

public record InsuranceTypeCalculationDetailDto(
        UUID ageCategoryId,
        UUID riskFactorId
) {
}
