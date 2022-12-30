package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrderDamagedProduct;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.PurchaseOrderDamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseOrderDamagedProductController {

    @Autowired
    private PurchaseOrderDamagedProductService purchaseOrderDamagedProductService;

    @PostMapping("purchaseOrder/{purchaseOrderId}/addDamagedProduct")
    public Message addPurchaseOrderDamagedProduct(@Valid @RequestBody PurchaseOrderDamagedProduct purchaseOrderDamagedProduct,
                                                  @PathVariable("purchaseOrderId") Long purchaseOrderId) throws NotFoundException {
        return purchaseOrderDamagedProductService.addPurchaseOrderDamagedProduct(purchaseOrderDamagedProduct, purchaseOrderId);
    }


    @GetMapping("/purchaseOrderDamagedProducts/page/{pageNo}")
    public List<PurchaseOrderDamagedProduct> fetchPurchaseOrderDamagedProductsList(@PathVariable int pageNo) {
        return purchaseOrderDamagedProductService.fetchPurchaseOrderDamagedProductsList(pageNo);
    }


    @GetMapping("/purchaseOrderDamagedProducts/{purchaseOrderDamagedProductsId}")
    public Optional<PurchaseOrderDamagedProduct> fetchCustomerReturnProductFromInvoiceById(@PathVariable Long purchaseOrderDamagedProductsId) throws NotFoundException {
        return purchaseOrderDamagedProductService.fetchPurchaseOrderDamagedProductsId(purchaseOrderDamagedProductsId);
    }
}
