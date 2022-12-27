package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

public interface CustomerReturnedDamagedProductsService {

    Message customerReturnProductFromInvoice(CustomerReturnedDamagedProducts customerReturnedDamagedProducts, Long invoiceId) throws NotFoundException;
}
