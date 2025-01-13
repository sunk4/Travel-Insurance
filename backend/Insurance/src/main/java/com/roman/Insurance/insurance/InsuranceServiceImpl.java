package com.roman.Insurance.insurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.enums.StatusOfPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepo;
    private final InsuranceMapper insuranceMapper;

    @Override
    public InsuranceEntity createInsurance (InsuranceDTO insuranceDTO,
                                            CustomerEntity customerEntity,
                                            double totalPrice, int tripLength) {
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        insuranceEntity.setStatusOfPayment(StatusOfPayment.UNPAID);
        insuranceEntity.setTripLength(tripLength);
        insuranceEntity.setTotalPrice(totalPrice);

        return insuranceRepo.save(insuranceEntity);

    }
}
