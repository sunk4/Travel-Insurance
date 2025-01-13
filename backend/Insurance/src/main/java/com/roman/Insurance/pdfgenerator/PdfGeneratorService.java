package com.roman.Insurance.pdfgenerator;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.insurance.InsuranceEntity;

public interface PdfGeneratorService {
    byte[] generatePdf(CustomerEntity customerEntity, InsuranceEntity insuranceEntity) throws Exception;

}
