package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface InvoiceService {
    public String saveInvoice(Invoice invoice, Long stockId) throws NotFoundException;

    public List<InvoiceStocksDTO> fetchByInvoiceId(Long invoiceId) throws NotFoundException;

    public List<InvoiceStocksDTO> fetchAllInvoice();

    public void updateInvoice(Long invoiceId, Invoice invoice) throws NotFoundException;

    public void deleteInvoice(Long invoiceId) throws NotFoundException;

    public List<InvoiceStocksDTO> getByInvoiceId(Long invoiceId);
}
