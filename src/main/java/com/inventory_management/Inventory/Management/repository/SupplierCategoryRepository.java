package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface SupplierCategoryRepository extends JpaRepository<SupplierCategory,Long> {

    Optional<SupplierCategory> findBySupplierCategoryNameContaining(String supplierCategoryName);


}
