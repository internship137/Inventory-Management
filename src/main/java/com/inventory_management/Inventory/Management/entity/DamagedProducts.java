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

    @Column(name = "damaged_product_name")
    private String damagedProductName;

    @NotBlank(message = "Product code should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Please provide a valid Product Code")
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_manufacturer")
    private String productManufacturer;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @NotNull(message = "Stock Quantity should not be empty")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Invalid quantity provided")
    @Column(name = "damaged_quantity")
    private String damagedQuantity;

    @Column(name = "supplierName")
    private String supplierName;

    @Column(name = "supplierCompany")
    private String supplierCompany;



}
