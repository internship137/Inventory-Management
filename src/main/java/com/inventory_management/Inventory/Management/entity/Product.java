package com.inventory_management.Inventory.Management.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;


    @Column(name = "product_code")
    private String productCode;


    @NotNull
//    @Size(min = 2, message = "Product name should be atleast 2 characters")
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_buying_price")
    private Long productBuyingPrice;

    @Column(name = "maximum_retail_price")
    private Long maximumRetailPrice;

    @Column(name="product_manufacturer")
    private String productManufacturer;

    @Column(name = "stock_quantity")
    private Long stockQuantity;

    @Column(name = "selling_price")
    private Long sellingPrice;

    @Column(name = "pricing_discount_percentage")
    private Long pricingDiscountPercentage;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="pricing_expire_date")
    private Date pricingExpireDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_date_time")
    private Date productCreatedDateTime = new Date(System.currentTimeMillis());

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

}
