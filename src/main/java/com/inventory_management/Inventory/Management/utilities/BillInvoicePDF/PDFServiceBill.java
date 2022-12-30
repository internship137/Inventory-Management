package com.inventory_management.Inventory.Management.utilities.BillInvoicePDF;


import com.inventory_management.Inventory.Management.dto.InvoiceDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PDFServiceBill {

    private List<InvoiceDTO> invoiceStocksDTOList;


    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTile1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile1.setSize(20);

        Font fontTile2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile2.setSize(18);

        Paragraph paragraph = new Paragraph("INVOICE", fontTile1);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph paragraph1 = new Paragraph("Company Name , \n Company Address , " +
                "\n Phone : 123456789 , Email : company@gmail.com ,\n  GSTIN : XXXXXXXXXXXXXX");
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph1.setSpacingBefore(30);


        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Paragraph paragraph2 = new Paragraph("Date of Issue : " + currentDateTime);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(20);

//        Product product = productRepository.findById(productId)

        Paragraph paragraph3 = new Paragraph("Invoice Details ");
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph3.setSpacingBefore(20);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);

        Paragraph paragraph4 = new Paragraph("Authoried Signatory" );
        paragraph4.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph4.setSpacingBefore(20);

        Paragraph paragraph5 = new Paragraph("Thank you for shopping with us.", fontTile2);
        paragraph5.setAlignment(Paragraph.ALIGN_BOTTOM);
        paragraph5.setSpacingBefore(200);

        writeTableHeader(table);
        writeTableData(table);

        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(table);
        document.add(paragraph4);
        document.add(paragraph5);

        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        cell.setPhrase(new Phrase("Invoice No:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Shipped To", font));
        table.addCell(cell);


        cell.setPhrase(new Phrase("Product Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("GST %", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

    }



    private void writeTableData(PdfPTable table) {
        for (InvoiceDTO invoiceDTO : invoiceStocksDTOList) {
            table.addCell(String.valueOf(invoiceDTO.getInvoiceId()));
            table.addCell(invoiceDTO.getCustomerName());
            table.addCell(invoiceDTO.getProductName());
            table.addCell(String.valueOf(invoiceDTO.getSellingQuantity()));
            table.addCell(invoiceDTO.getGstSlab());
            table.addCell(String.valueOf(invoiceDTO.getProductPrice()));

        }

    }

}
