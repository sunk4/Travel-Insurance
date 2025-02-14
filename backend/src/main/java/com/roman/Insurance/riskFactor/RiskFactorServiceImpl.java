package com.roman.Insurance.riskFactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RiskFactorServiceImpl implements RiskFactorService {
    private final RiskFactorRepository riskFactorRepository;
    private final RiskFactorMapper riskFactorMapper;

    @Override
    public List<RiskFactorDto> findAllRiskFactors () {

        List<RiskFactorEntity> riskFactorEntities = riskFactorRepository.findAll();
        return riskFactorMapper.entityListToDto(riskFactorEntities);

    }

    @Override
    public RiskFactorDto findRiskFactorById (UUID id) {
        RiskFactorEntity riskFactor = riskFactorRepository.findById(id).orElseThrow(() -> new RuntimeException("Risk factor not found"));

        return riskFactorMapper.toDto(riskFactor);
    }
}
