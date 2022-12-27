package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDamagedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_returned_products_id")
    private Long purchaseOrderDamageProductsId;

    @Column(name = "invoice_id")
    private Long purchaseOrderId;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "supplier_name")
    private String supplierName;

    private String purchaseOrderDamagedQuantity;
}
