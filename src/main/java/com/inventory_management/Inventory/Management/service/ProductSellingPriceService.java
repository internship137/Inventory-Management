package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductSellingPriceService {
    public ProductSellingPrice saveSellingPrice(Long productId, ProductSellingPrice productSellingPrice);

    public List<ProductSellingPrice> fetchAllPricing();

    public ProductSellingPrice fetchPricingById(Long sellingPriceId) throws NotFoundException;

    List<ProductSellingPrice> fetchPricingBySellingPrice(Long sellingPrice) throws NotFoundException;


    public String updateProductSellingPrice(Long sellingPriceId,
                                            ProductSellingPrice productSellingPrice) throws NotFoundException;

    public void deletePricing(Long sellingPriceId) throws NotFoundException;

}

