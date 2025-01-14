package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.CustomerDTO;
import com.roman.Insurance.insurance.InsuranceDTO;

public record CustomerInsuranceRequest(
        CustomerDTO customerDTO,
        InsuranceDTO insuranceDTO
) {

}
