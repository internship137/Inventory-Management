package com.inventory_management.Inventory.Management.utilities.BillInvoicePDF;

import com.inventory_management.Inventory.Management.dto.InvoiceDTO;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.InvoiceRepository;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import com.lowagie.text.DocumentException;
import lombok.Data;
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

@Data
@Controller
public class PDFExportControllerBill {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private final PDFServiceBill pdfService;


    @GetMapping("/pdf/billInvoice/generate/{invoiceId}")

    public void generatePDF(HttpServletResponse response, @PathVariable Long invoiceId) throws IOException
            , DocumentException, NotFoundException {
        {
            if (!invoiceRepository.existsById(invoiceId)) {
                throw new NotFoundException("Invoice with this id does not exist");
            }
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Invoice_" + currentDateTime + ".pdf";


            response.setHeader(headerKey, headerValue);


            List<InvoiceDTO> invoiceDTOList = invoiceService.getByInvoiceId(invoiceId);

            PDFServiceBill exporter = new PDFServiceBill(invoiceDTOList);
            exporter.export(response);

        }

    }
}
