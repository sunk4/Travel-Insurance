package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.ageCategories.AgeCategoryService;
import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.customer.MainCustomerService;
import com.roman.Insurance.encryption.EncryptionService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.riskFactor.RiskFactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuredPersonServiceImpl implements InsuredPersonService {
    private final InsuredPersonRepository insuredPersonRepository;
    private final InsuredPersonMapper insuredPersonMapper;
    private final EncryptionService encryptionService;
    private final AgeCategoryService ageCategoryService;
    private final RiskFactorService riskFactorService;
    private final InsuranceService insuranceService;

    @Override
    public List<UUID> createInsuredPerson (List<InsuredPersonDTO> insuredPersonDTOS, UUID insuranceId) throws Exception {

        List<InsuredPersonEntity> insuredPersonEntities = new ArrayList<>();

        for (InsuredPersonDTO insuredPersonDTO : insuredPersonDTOS) {
            InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
            insuredPersonEntity = encryptionService.encrypt(insuredPersonEntity);
            insuredPersonEntity.setAgeCategory(ageCategoryService.getAgeCategoryEntityById(insuredPersonDTO.ageCategoryId()));
            insuredPersonEntity.setRiskFactor(riskFactorService.getRiskFactorEntityById(insuredPersonDTO.riskFactorId()));
            insuredPersonEntity.setInsurance(insuranceService.getInsuranceEntityById(insuranceId));
            insuredPersonEntities.add(insuredPersonEntity);
        }

        List<InsuredPersonEntity> savedInsuredPersonEntities = insuredPersonRepository.saveAll(insuredPersonEntities);

        return savedInsuredPersonEntities.stream().map(InsuredPersonEntity::getId).toList();
    }
}
