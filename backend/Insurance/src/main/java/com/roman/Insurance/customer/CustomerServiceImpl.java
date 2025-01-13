package com.roman.Insurance.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public CustomerEntity createCustomer (CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
        customerRepository.save(customerEntity);

        return customerEntity;
    }
}
