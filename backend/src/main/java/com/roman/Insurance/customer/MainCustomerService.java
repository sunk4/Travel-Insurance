package com.roman.Insurance.customer;

import java.util.UUID;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerDto mainCustomerDTO) throws Exception;

    MainCustomerEntity getCustomerById (UUID customerId);
}
