package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseRequest;

public interface PurchaseRequestService {
    PurchaseRequest saveOrder(PurchaseRequest purchaseRequest, Long supplierId);

    void saveTokens(PurchaseRequest purchaseRequest, String token1, String token2);

    Message confirmOrderStatus(String token);

    Message rejectOrder(String reject);
}
