package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.PurchaseRequestVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRequestVerificationTokenRepository extends JpaRepository<PurchaseRequestVerificationToken, Long> {

    PurchaseRequestVerificationToken findByToken(String token);

    PurchaseRequestVerificationToken findByReject(String reject);
}