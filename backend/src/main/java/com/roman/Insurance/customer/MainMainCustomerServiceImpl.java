package com.roman.Insurance.customer;

import com.roman.Insurance.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MainMainCustomerServiceImpl implements MainCustomerService {
    private final MainCustomerRepository mainCustomerRepository;
    private final MainCustomerMapper customerMapper;
    private final EncryptionService encryptionService;

    @Override
    public MainCustomerEntity createCustomer (MainCustomerDTO mainCustomerDTO) throws Exception {

        MainCustomerEntity customerEntity = customerMapper.toEntity(mainCustomerDTO);
        MainCustomerEntity savedCustomerEntity = encryptionService.encrypt(customerEntity);
        mainCustomerRepository.save(savedCustomerEntity);

        return customerEntity;
    }

    @Override
    public MainCustomerEntity getCustomerById (UUID customerId) {

        return mainCustomerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

    }
}
