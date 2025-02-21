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
    public UUID createMainCustomer (MainCustomerDto mainCustomerDto) throws Exception {
        MainCustomerEntity customerEntity = customerMapper.toEntity(mainCustomerDto);
        MainCustomerEntity encryptedCustomerEntity = encryptionService.encrypt(customerEntity);
        return mainCustomerRepository.save(encryptedCustomerEntity).getId();

    }

    @Override
    public MainCustomerEntity getCustomerById (UUID customerId) {

        return mainCustomerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

    }
}
