package com.inventory_management.Inventory.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CategoryProductPricingDTO {


    private Long productId;

    private String productName;

    private String productCode;

    private Long productBuyingPrice;

    private Long maximumRetailPrice;

    private Long productSellingPrice;

    private Long stockQuantity;

    private String productManufacturer;


    //    private String supplier;

    private Long pricingDiscountPercentage;

    private Date pricingExpireDate;

    private Date productCreatedDateTime;

    private String category;

}