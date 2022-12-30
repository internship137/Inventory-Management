package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.ReturnedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnedProductsRepository extends JpaRepository<ReturnedProducts, Long> {
}
