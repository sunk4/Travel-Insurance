package com.roman.Insurance.customer;

import java.util.UUID;

public interface MainCustomerService {
    MainCustomerEntity createCustomer (MainCustomerDTO mainCustomerDTO) throws Exception;

    MainCustomerEntity getCustomerById (UUID customerId);
}
