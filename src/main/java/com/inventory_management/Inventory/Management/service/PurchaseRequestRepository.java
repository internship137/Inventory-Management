package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest,Long> {
}
