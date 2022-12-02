package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface InvoiceService {
    public Message saveInvoice(Invoice invoice, Long stockId) throws NotFoundException;

    public List<InvoiceStocksDTO> fetchByInvoiceId(Long invoiceId) throws NotFoundException;

    public List<InvoiceStocksDTO> fetchAllInvoice();

    public Message updateInvoice(Long invoiceId, Invoice invoice) throws NotFoundException;

    public Message deleteInvoice(Long invoiceId) throws NotFoundException;

    public List<InvoiceStocksDTO> getByInvoiceId(Long invoiceId);
}
