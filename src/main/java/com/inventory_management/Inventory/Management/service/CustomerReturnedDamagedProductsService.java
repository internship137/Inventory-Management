package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerReturnedDamagedProductsService {

    Message customerReturnProductFromInvoice(CustomerReturnedDamagedProducts customerReturnedDamagedProducts, Long invoiceId) throws NotFoundException;

    List<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceList(int pageNo);

    Optional<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceById(Long customerReturnProductsId) throws NotFoundException;
}
