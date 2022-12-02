package com.inventory_management.Inventory.Management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryProductPricingDTO {

    private Long productId;
    private String productName;
    private Long productCode;
    private String productDescription;
    private Long productBuyingPrice;
    private Long maximumRetailPrice;
    private Long productSellingPrice;
    private String productManufacturer;
    private Date productCreatedDateTime;
    private String category;

}