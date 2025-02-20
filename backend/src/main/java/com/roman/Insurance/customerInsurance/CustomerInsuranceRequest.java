package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.MainCustomerDTO;
import com.roman.Insurance.insurance.InsuranceDTO;

public record CustomerInsuranceRequest(
        MainCustomerDTO mainCustomerDTO,
        InsuranceDTO insuranceDTO

) {

}
