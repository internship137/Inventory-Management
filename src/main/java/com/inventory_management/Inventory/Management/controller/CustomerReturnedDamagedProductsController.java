package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.CustomerReturnedDamagedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class CustomerReturnedDamagedProductsController {

    @Autowired
    private CustomerReturnedDamagedProductsService customerReturnedDamagedProductsService;

    @PostMapping("invoice/{invoiceId}/addDamagedProduct")

    public Message customerReturnProductFromInvoice(@Valid @RequestBody CustomerReturnedDamagedProducts customerReturnedDamagedProducts,
                                                    @PathVariable("invoiceId") Long invoiceId) throws NotFoundException {
        return customerReturnedDamagedProductsService.customerReturnProductFromInvoice(customerReturnedDamagedProducts, invoiceId);
    }
}
