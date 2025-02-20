package com.roman.Insurance.encryption;

import com.roman.Insurance.customer.MainCustomerEntity;

public interface EncryptionService {
    MainCustomerEntity encrypt (MainCustomerEntity customerEntity) throws Exception;

    MainCustomerEntity decrypt (MainCustomerEntity customerEntity) throws Exception;
}
