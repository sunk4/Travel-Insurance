package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.enums.StatusOfPayment;

import java.util.UUID;

public interface InsuranceService {
    InsuranceEntity createInsurance (
            InsuranceDTO insuranceDTO,
            CustomerEntity customerEntity, double totalPrice, int tripLength
    );

    InsuranceEntity updateStatusOfPayment (UUID id, StatusOfPayment statusOfPayment);

    void updateUrlInsurancePayed (UUID id, String urlInsurancePayed);

    void updateUrlInsurancePreview (UUID id, String urlInsurancePreview);


    InsuranceEntity getInsuranceById (UUID id);
}
