package com.roman.Insurance.calculation;

import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.insuranceType.InsuranceTypeDto;
import com.roman.Insurance.insuranceType.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final InsuranceTypeService insuranceTypeService;
    private final CountryService countryService;

    @Override
    public CalculationDto calculatePrice (CalculationRequestDto calculationRequestDto) {
        CountryDto countryDto = countryService.findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory(calculationRequestDto.priceCalculationRequestDto());
        List<InsuranceTypeDto> insuranceTypes = insuranceTypeService.getAllCalculatedInsuranceTypesByDates(calculationRequestDto.insuranceTypeCalculationDto());

        return new CalculationDto(countryDto, insuranceTypes);
    }
}
