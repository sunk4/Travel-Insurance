package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customer.CustomerEntity;
import com.roman.Insurance.customer.CustomerService;
import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceEntity;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerInsurance")
@RequiredArgsConstructor
public class CustomerInsuranceController {
    private final CustomerService customerService;
    private final InsuranceService insuranceService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UploadService uploadService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<byte[]> createCustomerInsurance (@Valid @RequestBody CustomerInsuranceRequest customerInsuranceRequest) throws Exception {

        CustomerEntity customerEntity = customerService.createCustomer(customerInsuranceRequest.customerDTO());
        int tripLength = customerInsuranceRequest.insuranceDTO().startDate()
                .until(customerInsuranceRequest.insuranceDTO().endDate()).getDays();

        double totalPrice =
                insuranceService.calculateInsurancePrice(customerInsuranceRequest.customerDTO().age(),
                customerInsuranceRequest.insuranceDTO().continent(),
                tripLength,
                customerInsuranceRequest.insuranceDTO().type());
        InsuranceEntity insuranceEntity =
                insuranceService.createInsurance(customerInsuranceRequest.insuranceDTO(), customerEntity, totalPrice, tripLength);

        byte[] pdf = pdfGeneratorService.generatePdf(customerEntity,
                insuranceEntity);

        emailService.sendEmailWithGeneratedAttachment(customerEntity.getEmail(),
                "Insurance Policy",
                "emailTemplate",
                pdf,
                "InsurancePolicy_" + ".pdf");

        String fileName =
                "insurance_" + insuranceEntity.getId() + "_" + customerEntity.getLastName() +
                        ".pdf";
        String fileUrl = uploadService.uploadFileToS3(pdf, fileName);

        insuranceService.updateUrlInsurancePreview(insuranceEntity.getId(), fileUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + "filename=InsurancePolicy_" + ".pdf");
        headers.setContentLength(pdf.length);

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

    }
}
