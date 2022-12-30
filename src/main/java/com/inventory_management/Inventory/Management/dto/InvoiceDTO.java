package com.inventory_management.Inventory.Management.dto;


import lombok.Data;

@Data
public class InvoiceDTO {

    private Long invoiceId;
    private String productName;
    private String categoryName;
    private Long productPrice;
    private Long sellingQuantity;
    private String customerEmail;
    private String customerName;

}
