package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDamagedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_damaged_products_id")
    private Long purchaseOrderDamageProductsId;

    @Column(name = "purchase_order_id")
    private Long purchaseOrderId;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "supplier_name")
    private String supplierName;


    @Pattern(regexp = "^[0-9]{1,8}$", message = "enter a valid quantity")
    @Min(value = 1,message = "enter a valid quantity")
    @Column(name = "purchase_order_quantity")
    @NotBlank(message = "enter a valid quantity")
    private String purchaseOrderDamagedQuantity;
}
