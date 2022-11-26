package com.inventory_management.Inventory.Management.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class InvoiceStocksDTO {

    private Long invoiceId;
    private Date dateOfIssue;
    private String productName;
    private String categoryName;
    private Long productPrice;
    private Long sellingQuantity;

}
