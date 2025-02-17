package com.roman.Insurance.country;

import com.roman.Insurance.coverageRegions.CoverageRegionDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CountryDto(
        UUID id,
        String name,
        CoverageRegionDto coverageRegion,
        long days,
        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}
