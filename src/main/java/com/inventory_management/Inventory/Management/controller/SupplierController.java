package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.service.SupplierService;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Add Supplier

    @PostMapping("/supplier")
    public Message addSupplier(@RequestBody @Valid Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    // Get all suppliers

    @GetMapping("/supplier/{pageNo}/{recordCount}")
    public List<Supplier> fetchSupplierList(@PathVariable int pageNo,@PathVariable int recordCount) {
        return supplierService.fetchSupplierList(pageNo, recordCount);
    }

    // Get supplier By Id

    @GetMapping("/supplier/{supplierId}")
    public Optional<Supplier> fetchSupplierById(@PathVariable("supplierId") Long supplierId) throws NotFoundException {
        return supplierService.fetchSupplierById(supplierId);
    }

    // update supplier

    @PutMapping("/supplier/updateSupplier/{supplierId}")
    public Message updateSupplier(@PathVariable("supplierId") Long supplierId, @RequestBody Supplier supplier) throws NotFoundException {
        return supplierService.updateSupplier(supplierId, supplier);
    }

    // Delete Supplier

    @DeleteMapping("/supplier/delete/{supplierId}")
    public Message deleteSupplierById(@PathVariable("supplierId") Long supplierId) throws NotFoundException {
        return supplierService.deleteSupplierById(supplierId);
    }
}