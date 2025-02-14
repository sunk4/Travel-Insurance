package com.roman.Insurance.insuranceType;

import com.roman.Insurance.ageCategories.AgeCategoryService;
import com.roman.Insurance.country.CountryDto;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.coverageRegions.CoverageRegionDto;
import com.roman.Insurance.riskFactor.RiskFactorDto;
import com.roman.Insurance.riskFactor.RiskFactorService;
import com.roman.Insurance.utils.DateUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceTypeServiceImpl implements InsuranceTypeService {
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final InsuranceTypeMapper insuranceTypeMapper;
    private final DateUtilsService dateUtilsService;
    private final RiskFactorService riskFactorService;
    private final AgeCategoryService ageCategoryService;
    private final CountryService countryService;

    @Override
    public List<InsuranceTypeDto> getAllInsuranceTypes () {
        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        return insuranceTypeMapper.entityListToDto(insuranceTypes);
    }

    @Override
    public InsuranceTypeDto getInsuranceTypeById (UUID id) {
        InsuranceTypeEntity insuranceTypeEntity = insuranceTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance type not found"));

        return insuranceTypeMapper.toDto(insuranceTypeEntity);
    }

    @Override
    public List<InsuranceTypeDto> getAllAndCalculateThePriceOfInsuranceTypes (InsurancePriceCalculationRequest request) {
        long days =
                dateUtilsService.calculateDateDifferenceInDays(request.startDate(), request.endDate());

        CountryDto country = countryService.findCountryById(request.countryId());

             /*TODO
                   splitni do dvoch servisov - jeden v
                   InsuranceTypeServiceImpl, druhy v CountryServiceImpl or
                   CoverageRegion
                   Zmen InsurancePriceCalculationRequest iba pre InsuranceTypeServiceImpl
                   Vytvor controller - kde pouzijes obidva service a potom
                   vrat vystup pre zobrazenie - total price + data z oboch.


            */
        CoverageRegionDto updatedCoverageRegion =
                country.coverageRegion().withTotalCalculatedPrice(days * country.coverageRegion().basePricePerDay());

        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeRepository.findAll();
        List<InsuranceTypeDto> insuranceTypeDtos = insuranceTypeMapper.entityListToDto(insuranceTypes);

        List<InsuranceTypeDto> updatedInsuranceTypeDtos = insuranceTypeDtos.stream().map(insuranceTypeDto -> {
            double totalCalculatedPrice = days * insuranceTypeDto.basePricePerDay();
            return insuranceTypeDto.withTotalCalculatedPrice(totalCalculatedPrice);
        }).toList();

        return updatedInsuranceTypeDtos;
    }
}
