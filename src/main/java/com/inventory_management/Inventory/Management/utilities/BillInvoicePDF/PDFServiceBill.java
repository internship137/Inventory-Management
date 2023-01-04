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
        fontTile1.setSize(30);

        Font fontTile2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile2.setSize(12);

        Font fontTile3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile2.setSize(18);

        Font fontTile4 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile1.setSize(20);

        Paragraph paragraph = new Paragraph("INVOICE", fontTile1);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph paragraph1 = new Paragraph("Company Name , \n Company Address , " +
                "\n Phone : 123456789 , Email : company@gmail.com ,\n  GSTIN : XXXXXXXXXXXXXX",fontTile4);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph1.setSpacingBefore(30);


        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        Paragraph paragraph2 = new Paragraph("Date of Issue : " + currentDateTime,fontTile3);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(20);

        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.setSpacingBefore(20);


        Paragraph paragraph3 = new Paragraph("Purchase Details",fontTile3);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.setSpacingBefore(20);

        PdfPTable table2 = new PdfPTable(7);
        table2.setWidthPercentage(100);
        table2.setSpacingBefore(20);

        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(50);
        table3.setSpacingBefore(170);
        table3.setHorizontalAlignment(Element.ALIGN_RIGHT);


        Paragraph paragraph4 = new Paragraph("Authorised Signatory");
        paragraph4.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph4.setSpacingBefore(30);

        Paragraph paragraph5 = new Paragraph("Thank you for shopping with us.", fontTile2);
        paragraph5.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph5.setSpacingBefore(50);

        writeTableHeader(table1);
        writeTableData(table1);

        writeTableHeader2(table2);
        writeTableData2(table2);

        writeTableHeader3(table3);
        writeTableData3(table3);


        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(table1);
        document.add(paragraph3);
        document.add(table2);
        document.add(table3);
        document.add(paragraph4);
        document.add(paragraph5);

        document.close();

    }

    private void writeTableData3(PdfPTable table3) {
        for (InvoiceDTO invoiceDTO : invoiceStocksDTOList) {
            table3.addCell(String.valueOf(invoiceDTO.getGrandTotal()));
        }
    }

    private void writeTableHeader3(PdfPTable table3) {
        {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(4);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);

            cell.setPhrase(new Phrase("Grand Total", font));
            table3.addCell(cell);

        }
    }

    private void writeTableData(PdfPTable table1) {
        for (InvoiceDTO invoiceDTO : invoiceStocksDTOList) {
            table1.addCell(String.valueOf(invoiceDTO.getInvoiceId()));
            table1.addCell(invoiceDTO.getCustomerName());
        }
    }

    private void writeTableHeader(PdfPTable table1) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        cell.setPhrase(new Phrase("Invoice No:", font));
        table1.addCell(cell);

        cell.setPhrase(new Phrase("Shipped To", font));
        table1.addCell(cell);
    }


    private void writeTableHeader2(PdfPTable table2) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        cell.setPhrase(new Phrase("Sl No.", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Product Description", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Product Code", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Rate", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("SGST %", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("CGST %", font));
        table2.addCell(cell);

    }


    private void writeTableData2(PdfPTable table2) {
        for (InvoiceDTO invoiceDTO : invoiceStocksDTOList) {
            table2.addCell("1");
            table2.addCell(invoiceDTO.getProductName());
            table2.addCell(invoiceDTO.getProductCode());
            table2.addCell(String.valueOf(invoiceDTO.getSellingQuantity()));
            table2.addCell(String.valueOf(invoiceDTO.getProductPrice()));
            table2.addCell(String.valueOf(invoiceDTO.getSgst()));
            table2.addCell(String.valueOf(invoiceDTO.getCgst()));

        }

    }

}
