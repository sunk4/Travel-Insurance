package com.roman.Insurance.customer;

import com.roman.Insurance.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepository;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    @Override
    public CustomerEntity createCustomer (CustomerDTO customerDTO) throws Exception {

        CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
        CustomerEntity savedCustomerEntity = encryptionService.encrypt(customerEntity);
        customerRepository.save(savedCustomerEntity);

        return customerEntity;
    }
}
