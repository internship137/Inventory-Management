package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Save Invoice

    @PostMapping("/product/{stockId}/invoice")
    public Message saveInvoice(@RequestBody Invoice invoice,
                               @PathVariable Long stockId) throws NotFoundException {
        return invoiceService.saveInvoice(invoice,stockId);
    }

    // Get All invoice

    @GetMapping("/allInvoice")
    public List<InvoiceStocksDTO> fetchAllInvoice(){
        return invoiceService.fetchAllInvoice();
    }



    // Get invoice by Id

    @GetMapping("/invoice/product/{invoiceId}")
    public List<InvoiceStocksDTO> fetchByInvoiceId(@PathVariable Long invoiceId) throws NotFoundException {
        return invoiceService.fetchByInvoiceId(invoiceId);
    }

    // Update Invoice

    @PutMapping("/invoice/{invoiceId}")
    private String updateInvoice(@PathVariable Long invoiceId,
                               @RequestBody Invoice invoice) throws NotFoundException {
        invoiceService.updateInvoice(invoiceId,invoice);
        return "Updated Successfully";
    }

    // Delete Invoice

    @DeleteMapping("/invoice/delete/{invoiceId}")
    private String deleteInvoice(@PathVariable Long invoiceId) throws NotFoundException {
        invoiceService.deleteInvoice(invoiceId);
        return "Invoice Deleted Successfully";
    }


}
