package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;

public interface InsuranceService {
    void createInsurance (InsuranceDTO insuranceDTO, CustomerEntity customerEntity);
}
