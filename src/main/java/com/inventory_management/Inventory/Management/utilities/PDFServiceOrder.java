package com.inventory_management.Inventory.Management.utilities;


import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
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
public class PDFServiceOrder {

    private List<PlaceOrderSupplierStocksDTO> placeOrderSupplierStocksDTOList;

    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTile1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile1.setSize(28);

        Font fontTile2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTile2.setSize(18);

        Paragraph paragraph = new Paragraph("Order Invoice", fontTile1);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);


        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Paragraph paragraph1 = new Paragraph("Date of Issue : " + currentDateTime );
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setSpacingBefore(20);


        Paragraph paragraph2 = new Paragraph("Supplier Company Name \n Supplier Address " +
                "\n Phone : 123456789 \n Email : company@gmail.com " );
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(30);

        Paragraph paragraph3 = new Paragraph("Shipping Address");
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.setSpacingBefore(20);

        Paragraph paragraph4 = new Paragraph("Client Company Name \n Client Company Address " +
                "\n Phone : 123456789 \n Email : company@gmail.com " );
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph4.setSpacingBefore(30);

        Paragraph paragraph5 = new Paragraph("Order Details ");
        paragraph5.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph5.setSpacingBefore(20);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);


        writeTableHeader(table);
        writeTableData(table);

        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(table);
        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        cell.setPhrase(new Phrase("order_id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("supplier_product_name" , font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("supplier_category_name" , font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("supplier_product_price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("order_quantity" , font));
        table.addCell(cell);

    }


    private void writeTableData(PdfPTable table) {
        for (PlaceOrderSupplierStocksDTO placeOrderSupplierStocksDTO : placeOrderSupplierStocksDTOList){
            table.addCell(String.valueOf(placeOrderSupplierStocksDTO.getOrderId()));
            table.addCell(placeOrderSupplierStocksDTO.getSupplierProductName());
            table.addCell(placeOrderSupplierStocksDTO.getSupplierCategoryName());
            table.addCell(String.valueOf(placeOrderSupplierStocksDTO.getSupplierProductPrice()));
            table.addCell(String.valueOf(placeOrderSupplierStocksDTO.getOrderQuantity()));
        }

    }
}
