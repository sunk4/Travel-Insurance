package com.roman.Insurance.insuranceType;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insuranceType")
public class InsuranceTypeController {
    private final InsuranceTypeService insuranceTypeService;

    @GetMapping
    public ResponseEntity<List<InsuranceTypeDto>> getAllInsuranceTypes () {
        List<InsuranceTypeDto> insuranceTypes = insuranceTypeService.getAllInsuranceTypes();
        return ResponseEntity.ok(insuranceTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceTypeDto> getInsuranceTypeById (@PathVariable UUID id) {
        InsuranceTypeDto insuranceType = insuranceTypeService.getInsuranceTypeById(id);
        return ResponseEntity.ok(insuranceType);
    }

    @PostMapping
    public ResponseEntity<List<InsuranceTypeDto>> getAllAndCalculateThePriceOfInsuranceTypes (@RequestBody InsurancePriceCalculationRequest request) {
        List<InsuranceTypeDto> insuranceTypes = insuranceTypeService.getAllAndCalculateThePriceOfInsuranceTypes(request);
        return ResponseEntity.ok(insuranceTypes);
    }
}
