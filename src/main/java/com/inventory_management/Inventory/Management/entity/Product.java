package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank(message = "Product code should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Please provide a valid Product Code")
    @Column(name = "product_code")
    private String productCode;

    @NotBlank(message = "Product name should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Product Name")
    @Column(name = "product_name")
    private String productName;


    @NotBlank(message = "Product manufacturer should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Product manufacturer")
    @Column(name = "product_manufacturer")
    private String productManufacturer;

    @NotNull(message = "Stock Quantity should not be empty")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Please provide a valid stock quantity")
    @Min(0)
    @Max(9999999)
    @Column(name = "stock_quantity")
    private String stockQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_date_time")
    private Date productCreatedDateTime = new Date(System.currentTimeMillis());

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_pricing_id")
    private ProductPricing productPricing;

    @Column(name = "supplierName")
    private String supplierName;

    @Column(name = "supplierCompany")
    private String supplierCompany;

}
