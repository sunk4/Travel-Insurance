package com.roman.Insurance.insuredPerson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuredPersonDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        UUID ageCategoryId,
        UUID riskFactorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
