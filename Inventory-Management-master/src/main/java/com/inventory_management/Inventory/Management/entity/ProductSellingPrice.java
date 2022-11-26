package com.inventory_management.Inventory.Management.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSellingPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_price_id")
    private Long sellingPriceId;

    @Column(name = "selling_price")
    private Long sellingPrice;

    @Column(name = "pricing_discount_percentage")
    private Long pricingDiscountPercentage;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="pricing_expire_date")
    private Date pricingExpireDate;

}
