package com.roman.Insurance.encryption;

import com.roman.Insurance.customer.CustomerEntity;

public interface EncryptionService {
    CustomerEntity encrypt (CustomerEntity customerEntity) throws Exception;

    CustomerEntity decrypt (CustomerEntity customerEntity) throws Exception;
}
