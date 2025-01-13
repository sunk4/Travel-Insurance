package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepo;
    private final InsuranceMapper insuranceMapper;
    @Override
    public void createInsurance (InsuranceDTO insuranceDTO, CustomerEntity customerEntity) {
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        insuranceRepo.save(insuranceEntity);

    }
}
