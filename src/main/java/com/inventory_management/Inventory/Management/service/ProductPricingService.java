package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ProductPricing;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductPricingService {
    Message savePricing(ProductPricing productPricing, Long productId) throws NotFoundException;

    List<ProductPricing> fetchPricingList(int pageNo, int recordCount);

    ProductPricing fetchProductPricingById(Long pricingId) throws NotFoundException;

    Message updatePricing(Long pricingId, ProductPricing productPricing) throws NotFoundException;


}
