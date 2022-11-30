package com.inventory_management.Inventory.Management.utilities;

import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PDFExportControllerBill {

    @Autowired
    private InvoiceService invoiceService;

    private final PDFServiceBill pdfService;

    public PDFExportControllerBill(PDFServiceBill pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/pdf/billInvoice/generate/{invoiceId}")

    public void generatePDF(HttpServletResponse response , @PathVariable Long invoiceId) throws IOException
            , DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Bill_Invoice_" + currentDateTime + ".pdf";


        response.setHeader(headerKey, headerValue);


        List<InvoiceStocksDTO> invoiceStocksDTOList = invoiceService.getByInvoiceId(invoiceId);

        PDFServiceBill exporter = new PDFServiceBill(invoiceStocksDTOList);
        exporter.export(response);

    }


}
