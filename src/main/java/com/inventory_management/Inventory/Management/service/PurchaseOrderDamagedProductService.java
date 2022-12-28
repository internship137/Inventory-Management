package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrderDamagedProduct;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderDamagedProductService {
    Message addPurchaseOrderDamagedProduct(PurchaseOrderDamagedProduct purchaseOrderDamagedProduct, Long purchaseOrderId) throws NotFoundException;

    List<PurchaseOrderDamagedProduct> fetchPurchaseOrderDamagedProductsList(int pageNo);

    Optional<PurchaseOrderDamagedProduct> fetchPurchaseOrderDamagedProductsId(Long purchaseOrderDamagedProductsId) throws NotFoundException;
}
