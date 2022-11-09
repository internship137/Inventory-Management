package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierCategoryRepository extends JpaRepository<SupplierCategory,Long> {

    @Query(
            value = "select * from supplier_category s where s.supplier_category_name=?1",
            nativeQuery = true
    )
    Optional<SupplierCategory> findBySupplierCategoryNameIgnoreCase(String supplierCategoryName);
}
