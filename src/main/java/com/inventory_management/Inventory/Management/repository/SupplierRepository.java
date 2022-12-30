package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierCompanyIgnoreCase(String supplierCompany);

    boolean existsBySupplierContactIgnoreCase(String supplierContact);

    boolean existsBySupplierCompanyIgnoreCase(String supplierCompany);

    boolean existsBySupplierEmailIgnoreCase(String supplierCompany);
}