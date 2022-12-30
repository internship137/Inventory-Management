package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagedProductsRepository extends JpaRepository<DamagedProducts, Long> {
    DamagedProducts findByProductCodeIgnoreCase(String productCode);

    boolean existsByProductCodeIgnoreCase(String productCode);
}
