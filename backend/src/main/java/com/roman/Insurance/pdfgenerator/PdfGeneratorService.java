package com.roman.Insurance.pdfgenerator;

import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.insurance.InsuranceEntity;

public interface PdfGeneratorService {
    byte[] generatePdf(MainCustomerEntity customerEntity, InsuranceEntity insuranceEntity) throws Exception;
    byte[] generateInsurancePdf();

}
