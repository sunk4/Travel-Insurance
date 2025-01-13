package Encryption;

import com.roman.Insurance.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final EncryptionUtil encryption;

    @Override
    public CustomerEntity encrypt(CustomerEntity customerEntity) throws Exception {
        if (customerEntity.getFirstName() != null) {
            customerEntity.setEncryptedFistName(encryption.encrypt(customerEntity.getFirstName()));
        }
        if (customerEntity.getLastName() != null) {
            customerEntity.setEncryptedLastName(encryption.encrypt(customerEntity.getLastName()));
        }
        if (customerEntity.getEmail() != null) {
            customerEntity.setEncryptedEmail(encryption.encrypt(customerEntity.getEmail()));
        }
        if (customerEntity.getPhoneNumber() != null) {
            customerEntity.setEncryptedPhoneNumber(encryption.encrypt(customerEntity.getPhoneNumber()));
        }
        if (customerEntity.getAddress() != null) {
            customerEntity.setEncryptedAddress(encryption.encrypt(customerEntity.getAddress()));
        }
        if (customerEntity.getCity() != null) {
            customerEntity.setEncryptedCity(encryption.encrypt(customerEntity.getCity()));
        }
        if (customerEntity.getState() != null) {
            customerEntity.setEncryptedState(encryption.encrypt(customerEntity.getState()));
        }
        if (customerEntity.getZipCode() != null) {
            customerEntity.setEncryptedZipCode(encryption.encrypt(customerEntity.getZipCode()));
        }
        if (customerEntity.getPersonalIdentificationNumber() != null) {
            customerEntity.setEncryptedPersonalIdentificationNumber(encryption.encrypt(customerEntity.getPersonalIdentificationNumber()));
        }
        return customerEntity;
    }

    @Override
    public CustomerEntity decrypt(CustomerEntity customerEntity) throws Exception {
        if (customerEntity.getEncryptedFistName() != null) {
            customerEntity.setFirstName(encryption.decrypt(customerEntity.getEncryptedFistName()));
        }
        if (customerEntity.getEncryptedLastName() != null) {
            customerEntity.setLastName(encryption.decrypt(customerEntity.getEncryptedLastName()));
        }
        if (customerEntity.getEncryptedEmail() != null) {
            customerEntity.setEmail(encryption.decrypt(customerEntity.getEncryptedEmail()));
        }
        if (customerEntity.getEncryptedPhoneNumber() != null) {
            customerEntity.setPhoneNumber(encryption.decrypt(customerEntity.getEncryptedPhoneNumber()));
        }
        if (customerEntity.getEncryptedAddress() != null) {
            customerEntity.setAddress(encryption.decrypt(customerEntity.getEncryptedAddress()));
        }
        if (customerEntity.getEncryptedCity() != null) {
            customerEntity.setCity(encryption.decrypt(customerEntity.getEncryptedCity()));
        }
        if (customerEntity.getEncryptedState() != null) {
            customerEntity.setState(encryption.decrypt(customerEntity.getEncryptedState()));
        }
        if (customerEntity.getEncryptedZipCode() != null) {
            customerEntity.setZipCode(encryption.decrypt(customerEntity.getEncryptedZipCode()));
        }
        if (customerEntity.getEncryptedPersonalIdentificationNumber() != null) {
            customerEntity.setPersonalIdentificationNumber(encryption.decrypt(customerEntity.getEncryptedPersonalIdentificationNumber()));
        }
        return customerEntity;
    }
}
