package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ProductPricing;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.ProductPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductPricingController{

    @Autowired
    private ProductPricingService productPricingService;

    // Add Product Pricing

    @PostMapping("/product/pricing/{productId}")
    public Message savePricing(@Valid @RequestBody ProductPricing productPricing,
                               @PathVariable Long productId) throws NotFoundException {
        return productPricingService.savePricing(productPricing , productId);
    }

    // Get All Pricing (Pagination and Sorting)

    @GetMapping("/product/allPricing/{pageNo}/{recordCount}")
    public List<ProductPricing> fetchPricingList(@PathVariable int pageNo,
                                                 @PathVariable int recordCount) {
        return productPricingService.fetchPricingList(pageNo,recordCount);
    }

    // Get Pricing By Id


    @GetMapping("/product/pricing/{pricingId}")
    public ProductPricing fetchProductPricingById(@PathVariable("pricingId") Long pricingId) throws NotFoundException{
        return productPricingService.fetchProductPricingById(pricingId);
    }

    // Update Pricing

    @PutMapping("/product/updatePricing/{pricingId}")
    public Message updatePricing(@PathVariable Long pricingId,
                                 @RequestBody @Valid ProductPricing productPricing) throws NotFoundException{
        return productPricingService.updatePricing(pricingId , productPricing);
    }


}
