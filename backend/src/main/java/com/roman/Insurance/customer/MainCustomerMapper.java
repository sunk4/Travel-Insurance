package com.roman.Insurance.customer;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MainCustomerMapper {
    MainCustomerEntity toEntity (MainCustomerDTO mainCustomerDTO);

    MainCustomerDTO toDto (MainCustomerEntity customerEntity);

    List<MainCustomerDTO> entityListToDto (List<MainCustomerEntity> customerEntities);
}
