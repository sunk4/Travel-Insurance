package com.roman.Insurance.calculation;

import com.roman.Insurance.country.PriceCalculationRequestDto;
import com.roman.Insurance.insuranceType.InsuranceTypeCalculationDto;

public record CalculationRequestDto (
        PriceCalculationRequestDto priceCalculationRequestDto, InsuranceTypeCalculationDto insuranceTypeCalculationDto
,PickedInsuranceTypesDto pickedInsuranceTypesDto) {
}
