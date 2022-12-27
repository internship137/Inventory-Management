package com.inventory_management.Inventory.Management.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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



    @NotNull(message = "Product buying price should not be empty")
    @Pattern(regexp = "^[0-9]{1,7}$", message = "Please provide a valid Product Buying Price")
    @Min(1)@Max(9999999)
    @Column(name = "product_buying_price")
    private String productBuyingPrice;

    @NotNull(message = "Product MRP should not be empty")
    @Pattern(regexp = "^[0-9]{1,7}$", message = "Please provide a valid MRP")
    @Min(1)@Max(9999999)
    @Column(name = "maximum_retail_price")
    private String maximumRetailPrice;

    @NotBlank(message = "Product manufacturer should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$",message = "Please provide a valid Product manufacturer")
    @Column(name = "product_manufacturer")
    private String productManufacturer;

    @NotNull(message = "Stock Quantity should not be empty")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Stock Quantity should not be less than zero")
    @Min(1)@Max(9999999)
    @Column(name = "stock_quantity")
    private String stockQuantity;

    @Column(name = "selling_price")
    private Long sellingPrice;

    @NotNull(message = "Pricing discount percentage should not be empty")
    @Pattern(regexp = "^[0-9]{1,3}$", message = "Pricing discount percentage should be 0-100 percentage")
    @Min(1)@Max(100)
    @Column(name = "pricing_discount_percentage")
    private String pricingDiscountPercentage;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Please provide Discount Expire Date")
    @Future(message = "Enter a valid date")
    @Column(name = "pricing_expire_date")
    private Date pricingExpireDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_date_time")
    private Date productCreatedDateTime = new Date(System.currentTimeMillis());

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "supplierName")
    private String supplierName;

    @Column(name = "supplierCompany")
    private String supplierCompany;


}
