package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.PurchaseOrderVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderVerificationTokenRepository extends JpaRepository<PurchaseOrderVerificationToken, Long> {

    PurchaseOrderVerificationToken findByToken(String token);

    PurchaseOrderVerificationToken findByReject(String reject);
}