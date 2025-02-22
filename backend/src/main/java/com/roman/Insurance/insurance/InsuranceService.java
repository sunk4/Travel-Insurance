package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.enums.StatusOfPayment;

import java.util.UUID;

public interface InsuranceService {

    UUID createInsurance (InsuranceDTO insuranceDTO, UUID mainCustomerId,
                          double totalPrice);
    InsuranceEntity getInsuranceEntityById (UUID id);
}
