package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.enums.Continents;
import com.roman.Insurance.enums.InsuranceType;
import com.roman.Insurance.enums.StatusOfPayment;

import java.util.UUID;

public interface InsuranceService {
    InsuranceEntity createInsurance (InsuranceDTO insuranceDTO,
                       CustomerEntity customerEntity, double totalPrice, int tripLength);

    InsuranceEntity updateStatusOfPayment(UUID id, StatusOfPayment statusOfPayment);
    void updateUrlInsurancePayed(UUID id, String urlInsurancePayed);
    void updateUrlInsurancePreview(UUID id, String urlInsurancePreview);
    double calculateInsurancePrice(int age, Continents continent, int tripLength, InsuranceType type);
    InsuranceEntity getInsuranceById(UUID id);
}
