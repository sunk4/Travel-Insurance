package com.roman.Insurance.coverageRegions;

import java.util.UUID;

public record CoverageRegionDto(
        UUID id,
        String name,
        String description,
        int riskFactor


) {
}
