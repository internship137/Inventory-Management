package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DamagedProductsRepository extends JpaRepository<DamagedProducts, Long> {
    List<DamagedProducts> findByToReturnQuantityGreaterThan(Long toReturnQuantity);
    boolean existsByToReturnQuantityGreaterThan(Long toReturnQuantity);
    
    DamagedProducts findByProductCodeIgnoreCase(String productCode);

    boolean existsByProductCodeIgnoreCase(String productCode);
}
