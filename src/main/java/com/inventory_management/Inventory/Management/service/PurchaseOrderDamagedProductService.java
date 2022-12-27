package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrderDamagedProduct;
import com.inventory_management.Inventory.Management.error.NotFoundException;

public interface PurchaseOrderDamagedProductService {
    Message addPurchaseOrderDamagedProduct(PurchaseOrderDamagedProduct purchaseOrderDamagedProduct, Long purchaseOrderId) throws NotFoundException;
}
