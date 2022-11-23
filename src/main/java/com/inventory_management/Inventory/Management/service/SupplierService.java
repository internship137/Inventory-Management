package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    public List<Supplier> fetchSupplierList();

    public Optional<Supplier> fetchSupplierById(Long supplierId) throws NotFoundException;

    public List<Supplier> fetchSupplierByName(String supplierName) throws NotFoundException;

    public List<Supplier> fetchSupplierByCompanyName(String supplierCompany) throws NotFoundException;

    public Supplier updateSupplier(Long supplierId, Supplier supplier);

    public void deleteSupplierById(Long supplierId) throws NotFoundException;
}
