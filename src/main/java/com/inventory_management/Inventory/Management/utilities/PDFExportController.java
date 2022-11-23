package com.inventory_management.Inventory.Management.utilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {

    private final PDFService pdfService;

    public PDFExportController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/pdf/invoice/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Invoice_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfService.export(response);
    }
}
