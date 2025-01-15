package com.roman.Insurance.customer;

import java.util.UUID;

public interface CustomerService {
    CustomerEntity createCustomer (CustomerDTO customerDTO) throws Exception;

    CustomerEntity getCustomerById (UUID customerId);
}
