package com.roman.Insurance.insuranceType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeDto> getAllInsuranceTypes();
    InsuranceTypeDto getInsuranceTypeById(UUID id);

    List<InsuranceTypeDto> getAllCalculatedInsuranceTypesByDates (InsuranceTypeCalculationDto insuranceTypeCalculationDto);
}
