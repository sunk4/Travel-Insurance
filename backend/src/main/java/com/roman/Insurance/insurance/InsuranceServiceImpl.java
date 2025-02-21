package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.customer.MainCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepository;
    private final InsuranceMapper insuranceMapper;
    private final MainCustomerService mainCustomerService;

    @Override
    public UUID createInsurance (InsuranceDTO insuranceDTO, UUID mainCustomerId) {
        MainCustomerEntity customerEntity = mainCustomerService.getCustomerById(mainCustomerId);

        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        return insuranceRepository.save(insuranceEntity).getId();

    }
}
