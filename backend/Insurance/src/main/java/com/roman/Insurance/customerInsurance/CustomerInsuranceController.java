package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.customer.CustomerService;
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

    @PostMapping
    public ResponseEntity<Void> createCustomerInsurance (@Valid @RequestBody CustomerInsuranceRequest customerInsuranceRequest) {
        CustomerEntity customerEntity = customerService.createCustomer(customerInsuranceRequest.customerDTO());

        insuranceService.createInsurance(customerInsuranceRequest.insuranceDTO(), customerEntity);

        return ResponseEntity.ok().build();

    }
}
