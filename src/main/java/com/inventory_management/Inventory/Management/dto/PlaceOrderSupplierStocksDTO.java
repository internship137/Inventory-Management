package com.inventory_management.Inventory.Management.dto;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import lombok.Data;

@Data
public class PlaceOrderSupplierStocksDTO {
    private Long orderId;
    private String supplierProductName;
    private Long supplierProductPrice;
    private String supplierCategory;
    private Long orderQuantity;

}
