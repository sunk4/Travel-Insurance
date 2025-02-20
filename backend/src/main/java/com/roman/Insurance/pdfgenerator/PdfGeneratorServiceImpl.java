package com.roman.Insurance.pdfgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.roman.Insurance.customer.MainCustomerEntity;
import com.roman.Insurance.encryption.EncryptionService;
import com.roman.Insurance.insurance.InsuranceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    private final EncryptionService encryptionService;

    @Override
    public byte[] generatePdf (MainCustomerEntity customerEntity, InsuranceEntity insuranceEntity) throws Exception {
        MainCustomerEntity decryptedCustomer = encryptionService.decrypt(customerEntity);

        try (ByteArrayOutputStream pdfStream = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, pdfStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Travel Insurance", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            Paragraph companyInfo = new Paragraph(
                    """
                            Insurance Company
                            456 Imaginary Street
                            Bratislava, State 12345
                            Phone: +421 123 456 789
                            Email: random@gmail.com
                            """,
                    normalFont);
            companyInfo.setAlignment(Element.ALIGN_LEFT);
            companyInfo.setSpacingAfter(20);
            document.add(companyInfo);

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new int[]{1, 3});

            table.addCell(new PdfPCell(new Phrase("Client Name:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(decryptedCustomer.getFirstName() + " " + decryptedCustomer.getLastName(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Email:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(decryptedCustomer.getEmail(),
                    normalFont)));

            table.addCell(new PdfPCell(new Phrase("Phone Number:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(decryptedCustomer.getPhoneNumber(),
                    normalFont)));

            table.addCell(new PdfPCell(new Phrase("Address:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(
                    decryptedCustomer.getAddress() + ", " +
                            decryptedCustomer.getCity() + ", " +
                            decryptedCustomer.getState() + ", " +
                            decryptedCustomer.getZipCode(),
                    normalFont)));

            table.addCell(new PdfPCell(new Phrase("Insurance Type:", headerFont)));

            table.addCell(new PdfPCell(new Phrase("Continent:", headerFont)));

            table.addCell(new PdfPCell(new Phrase("Country:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insuranceEntity.getCountry().getName(), normalFont)));
            table.addCell(new PdfPCell(new Phrase("Start Date:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insuranceEntity.getStartDate().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("End Date:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insuranceEntity.getEndDate().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Trip Length (days):", headerFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(insuranceEntity.getTripLength()), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Total Price:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(String.format("$%.2f", insuranceEntity.getTotalPrice()), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Status of Payment:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insuranceEntity.getStatusOfPayment().toString(), normalFont)));

            document.add(table);

            Paragraph signatureSpacing = new Paragraph("\n\n");
            document.add(signatureSpacing);

            PdfPTable signatureTable = getPdfPTable(normalFont);

            document.add(signatureTable);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMM yyyy");

            Paragraph date =
                    new Paragraph("Date: " + formatter.format(java.time.LocalDate.now()), normalFont);
            date.setSpacingBefore(20);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            document.close();
            return pdfStream.toByteArray();

        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

    private static PdfPTable getPdfPTable (Font normalFont) throws DocumentException {
        PdfPTable signatureTable = new PdfPTable(2);
        signatureTable.setWidthPercentage(100);
        signatureTable.setSpacingBefore(10f);
        signatureTable.setWidths(new int[]{2, 2});

        PdfPCell clientSignatureCell = new PdfPCell(new Phrase("Client Signature: _______________", normalFont));
        clientSignatureCell.setBorder(Rectangle.NO_BORDER);
        signatureTable.addCell(clientSignatureCell);

        PdfPCell representativeSignatureCell = new PdfPCell(new Phrase("Representative Signature: _______________", normalFont));
        representativeSignatureCell.setBorder(Rectangle.NO_BORDER);
        representativeSignatureCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        signatureTable.addCell(representativeSignatureCell);
        return signatureTable;
    }
}
