package com.roman.Insurance.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping
    public ResponseEntity<CalculationDto> calculatePrice (@RequestBody CalculationRequestDto calculationRequestDto) {
        CalculationDto calculationResponseDto =
                calculationService.calculatePrice(calculationRequestDto);

        return ResponseEntity.ok(calculationResponseDto);
    }
}
