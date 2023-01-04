package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnedProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_products_id")
    private Long returnProductsId;

    @Column(name = "return_product_name")
    private String returnProductName;

    @Column(name = "product_code")
    private String productCode;


    //    @NotNull(message = "Stock Quantity should not be empty")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Invalid quantity provided")
    @Column(name = "return_quantity")
    private String returnQuantity;


    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_company")
    private String supplierCompany;

    @Column(name = "return_status")
    private String returnStatus;
}
