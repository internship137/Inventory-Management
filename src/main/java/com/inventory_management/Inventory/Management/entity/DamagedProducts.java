package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DamagedProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "damaged_products_id")
    private Long damagedProductsId;

    @Column(name = "product_name")
    private String productName;

    //    @NotBlank(message = "Product code should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Please provide a valid Product Code")
    @Column(name = "product_code")
    private String productCode;

//    @Column(name = "product_category")
//    private String productCategory;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_company")
    private String supplierCompany;

    @Column(name = "customer_return_quantity")
    private Long customerReturnQuantity = Long.valueOf(0);

    @Column(name = "purchase_order_damaged_quantity")
    private Long purchaseOrderDamagedQuantity = Long.valueOf(0);


    @Column(name = "to_return_quantity")
    private Long toReturnQuantity;

}
