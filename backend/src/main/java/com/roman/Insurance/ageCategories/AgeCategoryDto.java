package com.roman.Insurance.ageCategories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgeCategoryDto(
        UUID id,
        String name,
        Integer minAge,
        Integer maxAge,
        BigDecimal priceFactor,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
