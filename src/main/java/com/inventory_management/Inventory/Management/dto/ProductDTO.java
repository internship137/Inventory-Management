package com.inventory_management.Inventory.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private String productCode;

    private Long productBuyingPrice;

    private Long maximumRetailPrice;

    private Long productSellingPrice;

    private Long landingPrice;

    private String gstSlab;

    private Long stockQuantity;

    private String productManufacturer;

    private Date productCreatedDateTime;

    private String category;

    private String supplierName;

    private String supplierCompany;


}