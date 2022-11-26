package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    public List<Supplier> findBySupplierNameContaining(String supplierName)throws NotFoundException;

    public List<Supplier> findBySupplierCompanyContaining(String supplierCompany) throws NotFoundException;
}
