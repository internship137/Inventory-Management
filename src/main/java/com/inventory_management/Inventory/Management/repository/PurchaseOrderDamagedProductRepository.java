package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.PurchaseOrderDamagedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDamagedProductRepository extends JpaRepository<PurchaseOrderDamagedProduct, Long> {
    boolean existsByProductCodeIgnoreCaseAndPurchaseOrderId(String productCode, Long purchaseOrderId);


}
