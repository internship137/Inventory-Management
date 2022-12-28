package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.CustomerReturnedDamagedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class CustomerReturnedDamagedProductsController {

    @Autowired
    private CustomerReturnedDamagedProductsService customerReturnedDamagedProductsService;

    @PostMapping("invoice/{invoiceId}/addDamagedProduct")

    public Message customerReturnProductFromInvoice(@Valid @RequestBody CustomerReturnedDamagedProducts customerReturnedDamagedProducts,
                                                    @PathVariable("invoiceId") Long invoiceId) throws NotFoundException {
        return customerReturnedDamagedProductsService.customerReturnProductFromInvoice(customerReturnedDamagedProducts, invoiceId);
    }

    @GetMapping("/customerReturnProducts/page/{pageNo}")
    public List<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceList(@PathVariable int pageNo) {
        return customerReturnedDamagedProductsService.fetchCustomerReturnProductFromInvoiceList(pageNo);
    }


    @GetMapping("/customerReturnProducts/{customerReturnProductsId}")
    public Optional<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceById(@PathVariable Long customerReturnProductsId) throws NotFoundException {
        return customerReturnedDamagedProductsService.fetchCustomerReturnProductFromInvoiceById(customerReturnProductsId);
    }
}
