package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.dto.InvoiceDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Save Invoice

    @PostMapping("/product/{productId}/invoice")
    public Message saveInvoice(@RequestBody @Valid Invoice invoice,
                               @PathVariable Long productId) throws NotFoundException {
        return invoiceService.saveInvoice(invoice, productId);
    }

    // Get All invoice (Pagination and Sorting)

    @GetMapping("/allInvoice/{pageNo}/{recordCount}")
    public List<InvoiceDTO> fetchAllInvoice(@PathVariable int pageNo,
                                            @PathVariable int recordCount) {
        return invoiceService.fetchAllInvoice(pageNo,recordCount);
    }



    // Get invoice by Id

    @GetMapping("/invoice/product/{invoiceId}")
    public List<InvoiceDTO> fetchByInvoiceId(@PathVariable Long invoiceId) throws NotFoundException {
        return invoiceService.fetchByInvoiceId(invoiceId);
    }


    // Delete Invoice

    @DeleteMapping("/invoice/delete/{invoiceId}")
    private String deleteInvoice(@PathVariable Long invoiceId) throws NotFoundException {
        invoiceService.deleteInvoice(invoiceId);
        return "Invoice Deleted Successfully";
    }


}
