package com.roman.Insurance.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> findAllCountries () {
        List<CountryEntity> countryEntities = countryRepository.findAll();

        return countryMapper.entityListToDto(countryEntities);
    }

    @Override
    public CountryDto findCountryById (UUID id) {
        CountryEntity countryEntity =
                countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));

        return countryMapper.countryEntityToCountryDto(countryEntity);
    }

}
