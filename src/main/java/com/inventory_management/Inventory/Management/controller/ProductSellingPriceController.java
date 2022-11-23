package com.inventory_management.Inventory.Management.controller;



import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.ProductSellingPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductSellingPriceController {

    @Autowired
    private ProductSellingPriceService productSellingPriceService;


    // Add Pricing to products


    @PostMapping("/product/{productId}/addSellingPrice")
    public ProductSellingPrice saveSellingPrice(@PathVariable Long productId,
                                                @RequestBody ProductSellingPrice productSellingPrice) {
        return productSellingPriceService.saveSellingPrice( productId, productSellingPrice);

    }

    // Get all pricing

    @GetMapping("/allPricing")
    public List<ProductSellingPrice> fetchAllPricing() {
        return productSellingPriceService.fetchAllPricing();
    }

    // Get Pricing by Id

    @GetMapping("/pricing/{sellingPriceId}")
    public ProductSellingPrice fetchPricingById(@PathVariable("sellingPriceId") Long sellingPriceId) throws NotFoundException {
        return productSellingPriceService.fetchPricingById(sellingPriceId);
    }

    // Get pricing by selling price

    @GetMapping("/pricing/sellingPrice/{sellingPrice}")
    public List<ProductSellingPrice> fetchPricingBySellingPrice(@PathVariable("sellingPrice") Long sellingPrice) throws NotFoundException {
        return productSellingPriceService.fetchPricingBySellingPrice(sellingPrice);

    }

    // update Product Selling Price


    @PutMapping("/productSellingPrice/{sellingPriceId}")
    public String updateProductSellingPrice(@PathVariable Long sellingPriceId,
                                            @RequestBody ProductSellingPrice productSellingPrice) {
        return productSellingPriceService.updateProductSellingPrice(sellingPriceId, productSellingPrice);
    }


    // delete a product


    @DeleteMapping("/pricing/delete/{sellingPriceId}")
    public String deletePricingById(@PathVariable Long sellingPriceId) throws NotFoundException {
        productSellingPriceService.deletePricing(sellingPriceId);
        return "Selling Price Deleted !";
    }

}
