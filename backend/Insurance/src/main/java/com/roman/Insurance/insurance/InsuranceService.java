package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;

public interface InsuranceService {
    InsuranceEntity createInsurance (InsuranceDTO insuranceDTO,
                       CustomerEntity customerEntity, double totalPrice, int tripLength);
}
