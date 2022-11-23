package com.inventory_management.Inventory.Management.dto;


import lombok.Data;

@Data
public class StocksDTO {

    private Long stockId;
    private Long stockQuantity;
    private String product;
    private String category;
    private String supplier;
}
