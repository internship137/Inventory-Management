package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_pricing_id")
    private Long productPricingId;

    @NotNull(message = "Product buying price should not be empty")
    @Pattern(regexp = "^[0-9]{1,7}$", message = "Please provide a valid Product Buying Price")
    @Min(1)@Max(1000000)
    @Column(name = "product_buying_price")
    private String  productBuyingPrice;

    @NotNull(message = "GST Slab should not be empty")
    @Pattern(regexp="^18$|^28$", message="GST can either be 18 or 28 only")
    @Column(name = "gst_slab")
    private String gstSlab;

    @Column(name = "landing_price")
    private Long landingPrice;

    @NotNull(message = "Profit Margin should not be empty")
    @Pattern(regexp = "^[0-9]{1,7}$", message = "Please provide a valid Profit Margin")
    @Min(0)@Max(1000000)
    @Column(name = "profit_margin")
    private String profitMargin;

    @NotNull(message = "Product MRP should not be empty")
    @Pattern(regexp = "^[0-9]{1,7}$", message = "Please provide a valid MRP")
    @Min(1)@Max(1000000)
    @Column(name = "maximum_retail_price")
    private String maximumRetailPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pricing_created_date_time")
    private Date pricingCreatedDateTime = new Date(System.currentTimeMillis());

    @Column(name = "product_selling_price")
    private Long productSellingPrice;

    @Column(name = "product_code")
    private String productCode;

    @Column(name="sgst")
    private Long sgst;

    @Column(name="cgst")
    private Long cgst;

}
