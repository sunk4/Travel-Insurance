package com.roman.Insurance.insuranceType;

import com.roman.Insurance.calculation.PickedInsuranceTypesDto;

import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeDto> getAllInsuranceTypes();
    InsuranceTypeDto getInsuranceTypeById(UUID id);

    List<InsuranceTypeDto> getAllCalculatedInsuranceTypesByDates (InsuranceTypeCalculationDto insuranceTypeCalculationDto);

    List<InsuranceTypeDto> getPickedInsuranceTypes (PickedInsuranceTypesDto pickedInsuranceTypesDto, List<InsuranceTypeDto> insuranceTypes);
}
