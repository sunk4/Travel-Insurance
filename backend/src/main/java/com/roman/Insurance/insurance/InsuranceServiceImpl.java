package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.enums.InsuranceType;
import com.roman.Insurance.enums.StatusOfPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepo;
    private final InsuranceMapper insuranceMapper;

    @Override
    public InsuranceEntity createInsurance (
            InsuranceDTO insuranceDTO,
            CustomerEntity customerEntity,
            double totalPrice, int tripLength
    ) {
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        insuranceEntity.setStatusOfPayment(StatusOfPayment.UNPAID);
        insuranceEntity.setTripLength(tripLength);
        insuranceEntity.setTotalPrice(totalPrice);

        return insuranceRepo.save(insuranceEntity);

    }

    @Override
    public InsuranceEntity updateStatusOfPayment (UUID id, StatusOfPayment statusOfPayment) {

        InsuranceEntity insuranceEntity =
                insuranceRepo.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
        insuranceEntity.setStatusOfPayment(statusOfPayment);
        return insuranceRepo.save(insuranceEntity);
    }

    @Override
    public void updateUrlInsurancePayed (UUID id, String urlInsurancePayed) {

    }

    @Override
    public void updateUrlInsurancePreview (UUID id, String urlInsurancePreview) {
        InsuranceEntity insuranceEntity =
                insuranceRepo.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
        insuranceEntity.setUrlInsurancePreview(urlInsurancePreview);
        insuranceRepo.save(insuranceEntity);
    }

    @Override
    public double calculateInsurancePrice (int age,  int tripLength, InsuranceType type) {
        double price = 2.0;

        if (age >= 0 && age <= 17) {
            price *= 1.5;
        } else if (age >= 18 && age <= 69) {
            price *= 1.1;
        } else {
            price *= 1.8;
        }

        switch (type) {
            case Basic:
                break;
            case Standard:
                price *= 1.2;
                break;
            case Premium:
                price *= 1.4;
                break;
        }

        return (double) Math.round(price * tripLength * 100) / 100;
    }

    @Override
    public InsuranceEntity getInsuranceById (UUID id) {
        return insuranceRepo.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
    }
}
