package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.customer.CustomerService;
import com.roman.Insurance.insurance.InsuranceEntity;
import com.roman.Insurance.insurance.InsurancePriceCalculator;
import com.roman.Insurance.insurance.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerInsurance")
@RequiredArgsConstructor
public class CustomerInsuranceController {
    private final CustomerService customerService;
    private final InsuranceService insuranceService;
    private final InsurancePriceCalculator insurancePriceCalculator;

    @PostMapping
    public ResponseEntity<Void> createCustomerInsurance (@Valid @RequestBody CustomerInsuranceRequest customerInsuranceRequest) throws Exception {

        CustomerEntity customerEntity = customerService.createCustomer(customerInsuranceRequest.customerDTO());
        int tripLength = customerInsuranceRequest.insuranceDTO().startDate()
                .until(customerInsuranceRequest.insuranceDTO().endDate()).getDays();

        double totalPrice = insurancePriceCalculator.calculateInsurancePrice(customerInsuranceRequest.customerDTO().age(),
                customerInsuranceRequest.insuranceDTO().continent(),
                tripLength,
                customerInsuranceRequest.insuranceDTO().type());
        InsuranceEntity insuranceEntity =
                insuranceService.createInsurance(customerInsuranceRequest.insuranceDTO(), customerEntity, totalPrice, tripLength);

        return ResponseEntity.ok().build();

    }
}
