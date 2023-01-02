package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Message addSupplier(Supplier supplier);
    public List<Supplier> fetchSupplierList(int pageNo,int recordCount);

    public Optional<Supplier> fetchSupplierById(Long supplierId) throws NotFoundException;

    public Message updateSupplier(Long supplierId, Supplier supplier) throws NotFoundException;

    public Message deleteSupplierById(Long supplierId) throws NotFoundException;

}