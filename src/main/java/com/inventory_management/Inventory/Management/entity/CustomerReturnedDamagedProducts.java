package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReturnedDamagedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_returned_products_id")
    private Long CustomerReturnedProductsId;

    @Column(name = "invoice_id")
    private Long invoiceId;


    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_name")
    private String customerName;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    private String customerReturnQuantity;
}
