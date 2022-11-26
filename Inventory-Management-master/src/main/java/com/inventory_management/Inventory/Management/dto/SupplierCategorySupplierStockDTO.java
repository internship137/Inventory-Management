package com.inventory_management.Inventory.Management.dto;

import lombok.Data;

@Data
public class SupplierCategorySupplierStockDTO {

    private Long supplierStocksId;
    private String supplierProductName;
    private String supplierCategory;
    private Long supplierProductPrice;
    private Long supplierProductQuantity;
}
