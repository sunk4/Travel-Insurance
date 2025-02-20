package com.roman.Insurance.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainCustomerRepository extends JpaRepository<MainCustomerEntity, UUID> {
}
