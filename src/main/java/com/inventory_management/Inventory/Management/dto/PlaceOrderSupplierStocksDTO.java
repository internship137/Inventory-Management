package com.inventory_management.Inventory.Management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlaceOrderSupplierStocksDTO {
    private Long orderId;
    private String supplierProductName;
    private Long supplierProductPrice;
    private String supplierCategoryName;
    private Long orderQuantity;
    private Date orderPlacedDate;
    private String orderStatus;

}
