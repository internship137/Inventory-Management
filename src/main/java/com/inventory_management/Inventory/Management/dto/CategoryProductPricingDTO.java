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

//    @NotNull(message = "Product name should not be empty")
    private Long productId;
//    @NotNull(message = "Product name should not be empty")
//    @NotNull(message = "Product name should not be empty")
//    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Product name must be of 1 to 70 length with no special characters")
    private String productName;

//    @NotNull(message = "Product code should not be empty")
//    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Product code must be of 1 to 70 length with no special characters")

//    @NotNull(message = "Product name should not be empty")
    private String productCode;
//    @NotNull(message = "Product buying price should not be empty")
//    @Pattern(regexp = "^[0-9]{1,7}$", message = "Product buying price must be of 1 to 7 length with no special characters")

    private Long productBuyingPrice;
    private Long maximumRetailPrice;
    private Long productSellingPrice;
    private Long stockQuantity;
    private String productManufacturer;
    //    private String supplier;

    private Date productCreatedDateTime;
    private String category;

}