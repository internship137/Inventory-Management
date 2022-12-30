package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.ProductPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricingRepository extends JpaRepository<ProductPricing, Long> {

    boolean existsByProductCodeIgnoreCase(String productCode);

}


