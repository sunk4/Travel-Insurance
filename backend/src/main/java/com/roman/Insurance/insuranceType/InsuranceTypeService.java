package com.roman.Insurance.insuranceType;

import java.util.List;
import java.util.UUID;

public interface InsuranceTypeService {

    List<InsuranceTypeDto> getAllInsuranceTypes();
    InsuranceTypeDto getInsuranceTypeById(UUID id);

    List<InsuranceTypeDto> getAllAndCalculateThePriceOfInsuranceTypes (InsurancePriceCalculationRequest request);
}
