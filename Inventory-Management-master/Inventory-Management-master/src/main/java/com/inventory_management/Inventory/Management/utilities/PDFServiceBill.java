package com.inventory_management.Inventory.Management.utilities;


import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
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
//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PDFServiceBill {

    private List<InvoiceStocksDTO> invoiceStocksDTOList;

//    public PDFService(List<InvoiceStocksDTO> invoiceStocksDTOList) {            // constructors
//        this.invoiceStocksDTOList = invoiceStocksDTOList;
//
//    }


    public void export(HttpServletResponse response) throws IOException , DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTile = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile.setSize(28);

        Paragraph paragraph = new Paragraph("Bill Invoice", fontTile);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingBefore(20);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Paragraph paragraph1 = new Paragraph("Date : " + currentDateTime );
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setSpacingBefore(20);


//        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
//        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("Company Name \n Company Address " +
                "\n Phone : 123456789 \n Email : company@gmail.com " );
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph3 = new Paragraph("Sale Details ");
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.setSpacingBefore(20);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);



        writeTableHeader(table);
        writeTableData(table);



        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(table);
        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("invoice_id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("date_of_issue" , font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("product_name" , font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("category_name" , font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("product_price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("selling_quantity" , font));
        table.addCell(cell);

    }



    private void writeTableData(PdfPTable table) {
        for (InvoiceStocksDTO invoiceStocksDTO : invoiceStocksDTOList){
            table.addCell(String.valueOf(invoiceStocksDTO.getInvoiceId()));
            table.addCell(String.valueOf(invoiceStocksDTO.getDateOfIssue()));
            table.addCell(invoiceStocksDTO.getProductName());
            table.addCell(invoiceStocksDTO.getCategoryName());
            table.addCell(String.valueOf(invoiceStocksDTO.getProductPrice()));
            table.addCell(String.valueOf(invoiceStocksDTO.getSellingQuantity()));
        }

    }

}
