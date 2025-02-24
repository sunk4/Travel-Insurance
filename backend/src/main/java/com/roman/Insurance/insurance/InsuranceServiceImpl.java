package com.roman.Insurance.insurance;

import com.roman.Insurance.country.CountryEntity;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.customer.MainCustomerService;
import com.roman.Insurance.enums.StatusOfPayment;
import com.roman.Insurance.insuranceType.InsuranceTypeEntity;
import com.roman.Insurance.insuranceType.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepository;
    private final InsuranceMapper insuranceMapper;
    private final MainCustomerService mainCustomerService;
    private final CountryService countryService;
    private final InsuranceTypeService insuranceTypeService;


    @Override
    public UUID createInsurance (InsuranceDTO insuranceDTO,
                                 UUID mainCustomerId,double totalPrice) {
        MainCustomerEntity customerEntity = mainCustomerService.getCustomerById(mainCustomerId);
        CountryEntity countryEntity =
                countryService.findCountryEntityById(insuranceDTO.countryId());
        System.out.println(insuranceDTO);
        System.out.println(insuranceDTO.insuranceTypeIds());
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        insuranceEntity.setStatusOfPayment(StatusOfPayment.UNPAID);
        insuranceEntity.setCountry(countryEntity);
        insuranceEntity.setTotalPrice(totalPrice);

        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeService.getAllInsuranceTypesEntitiesByIds(insuranceDTO.insuranceTypeIds());
        insuranceEntity.setInsuranceTypes(insuranceTypes);

        return insuranceRepository.save(insuranceEntity).getId();

    }

    @Override
    public InsuranceEntity getInsuranceEntityById (UUID id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
    }
}
