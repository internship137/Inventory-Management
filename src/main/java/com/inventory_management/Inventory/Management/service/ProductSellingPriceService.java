package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductSellingPriceService {
    public ProductSellingPrice saveSellingPrice(Long productId, ProductSellingPrice productSellingPrice) throws NotFoundException;

    public List<ProductSellingPrice> fetchAllPricing();

    public ProductSellingPrice fetchPricingById(Long sellingPriceId) throws NotFoundException;

    List<ProductSellingPrice> fetchPricingBySellingPrice(Long sellingPrice) throws NotFoundException;


    public Message updateProductSellingPrice(Long sellingPriceId,
                                             ProductSellingPrice productSellingPrice) throws NotFoundException;


}

