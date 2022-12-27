package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrderDamagedProduct;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.PurchaseOrderDamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseOrderDamagedProductController {

    @Autowired
    private PurchaseOrderDamagedProductService purchaseOrderDamagedProductService;

    @PostMapping("purchaseOrder/{purchaseOrderId}/addDamagedProduct")
    public Message addPurchaseOrderDamagedProduct(@Valid @RequestBody PurchaseOrderDamagedProduct purchaseOrderDamagedProduct,
                                                  @PathVariable("purchaseOrderId") Long purchaseOrderId) throws NotFoundException {
        return purchaseOrderDamagedProductService.addPurchaseOrderDamagedProduct(purchaseOrderDamagedProduct,purchaseOrderId);
    }
}
