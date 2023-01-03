package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) throws NotFoundException;

    void saveTokens(PurchaseOrder purchaseOrder, String token1, String token2);

    Message confirmOrderStatus(String token);

    Message rejectOrder(String reject);

    List<PurchaseOrder> fetchAllRequest(int pageNo);


    Optional<PurchaseOrder> fetchRequestsById(Long purchaseRequestId) throws NotFoundException;

    Message changeStatus(Long purchaseRequestId) throws NotFoundException;
}
