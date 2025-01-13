package com.roman.Insurance.insurance;

import com.roman.Insurance.enums.Continents;
import com.roman.Insurance.enums.InsuranceType;

public interface InsurancePriceCalculator {
    double calculateInsurancePrice(int age, Continents continent, int tripLength, InsuranceType type);
}
