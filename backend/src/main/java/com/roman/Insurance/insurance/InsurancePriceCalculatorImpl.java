package com.roman.Insurance.insurance;

import com.roman.Insurance.enums.Continents;
import com.roman.Insurance.enums.InsuranceType;
import org.springframework.stereotype.Service;

@Service
public class InsurancePriceCalculatorImpl implements InsurancePriceCalculator {
    @Override
    public double calculateInsurancePrice (int age, Continents continent, int tripLength, InsuranceType type) {
        double price = 2.0;


        if (age >= 0 && age <= 17) {
            price *= 1.5;
        } else if (age >= 18 && age <= 69) {
            price *= 1.1;
        } else {
            price *= 1.8;
        }

        switch (continent) {
            case Africa:
                price *= 2.5;
                break;
            case Antarctica:
                price *= 3;
                break;
            case Asia:
            case SouthAmerica:
                price *= 2;
                break;
            case Australia:
                price *= 1.8;
                break;
            case NorthAmerica:
            case Europe:
                price *= 1.5;
                break;

        }

        switch (type) {
            case Basic:
                break;
            case Standard:
                price *= 1.2;
                break;
            case Premium:
                price *= 1.4;
                break;
        }

        return (double) Math.round(price * tripLength * 100) / 100;
    }
}

