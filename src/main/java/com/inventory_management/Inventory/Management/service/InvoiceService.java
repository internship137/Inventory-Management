package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.InvoiceDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface InvoiceService {
    public Message saveInvoice(Invoice invoice, Long productId) throws NotFoundException;

    public List<InvoiceDTO> fetchByInvoiceId(Long invoiceId) throws NotFoundException;

    public List<InvoiceDTO> fetchAllInvoice(int pageNo, int recordCount);

    public Message deleteInvoice(Long invoiceId) throws NotFoundException;

    public List<InvoiceDTO> getByInvoiceId(Long invoiceId) throws NotFoundException;
}
