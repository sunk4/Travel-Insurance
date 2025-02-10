package com.roman.Insurance.country;


import com.roman.Insurance.coverageRegions.CoverageRegionDto;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryDto> findAllCountries();

    CountryDto findCountryById (UUID id);
}
