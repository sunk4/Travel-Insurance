package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.InsuranceCalculationResponse;
import com.roman.Insurance.calculation.PickedInsuranceTypesDto;
import com.roman.Insurance.country.CountryDto;

import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeDto> getAllInsuranceTypes();
    InsuranceTypeDto getInsuranceTypeById(UUID id);

    List<InsuranceTypeDto> getAllCalculatedInsuranceTypesByDates (InsuranceTypeCalculationDto insuranceTypeCalculationDto);

    InsuranceCalculationResponse getPickedInsuranceTypes (PickedInsuranceTypesDto pickedInsuranceTypesDto, List<InsuranceTypeDto> insuranceTypes, CountryDto countryDto);
}
