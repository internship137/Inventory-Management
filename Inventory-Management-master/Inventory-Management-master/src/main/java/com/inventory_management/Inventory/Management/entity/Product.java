package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Long productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name = "product_buying_price")
    private Long productBuyingPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "selling_price_id", referencedColumnName = "selling_price_id")        // pricing
    private ProductSellingPrice productSellingPrice;

    @Column(name="product_manufacturer")
    private String productManufacturer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_date_time")
    private Date productCreatedDateTime = new Date(System.currentTimeMillis());

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

}
