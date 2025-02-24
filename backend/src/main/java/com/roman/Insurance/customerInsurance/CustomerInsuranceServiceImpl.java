package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.calculation.CalculationService;
import com.roman.Insurance.customer.MainCustomerService;
import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.insuredPerson.InsuredPersonService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import com.roman.Insurance.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerInsuranceServiceImpl implements CustomerInsuranceService {
    private final MainCustomerService customerService;
    private final InsuredPersonService insurePersonService;
    private final InsuranceService insuranceService;
    private final CalculationService calculationService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UploadService uploadService;
    private final EmailService emailService;
    private final StripeService stripeService;
    @Override
    public void createTravelInsurance (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) throws Exception {
        UUID mainCustomerId = customerService.createMainCustomer(customerTravelInsuranceRequest.mainCustomerDto());


        double totalPrice = calculationService.calculatePriceTotal(customerTravelInsuranceRequest);
        UUID insuranceId =
                insuranceService.createInsurance(customerTravelInsuranceRequest.insuranceDTO(), mainCustomerId, totalPrice);

        List<UUID> insuredPersonIds =
                insurePersonService.createInsuredPerson(customerTravelInsuranceRequest.insuredPersonDTO(), insuranceId);


    }
}
