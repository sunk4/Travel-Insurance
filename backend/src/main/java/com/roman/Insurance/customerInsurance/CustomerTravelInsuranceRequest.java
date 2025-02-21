package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.MainCustomerDto;
import com.roman.Insurance.insuredPerson.InsuredPersonDTO;

import java.util.List;

public record CustomerTravelInsuranceRequest(
        MainCustomerDto mainCustomerDto,
        List<InsuredPersonDTO> insuredPersonDTO
//        InsuranceDTO insuranceDTO

) {

}
