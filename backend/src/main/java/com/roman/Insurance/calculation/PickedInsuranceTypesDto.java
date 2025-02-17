package com.roman.Insurance.calculation;

import java.util.List;
import java.util.UUID;

public record PickedInsuranceTypesDto (List<UUID> insuranceTypes) {
}
