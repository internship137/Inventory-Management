package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.dto.CategoryProductPricingDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductService {

    Message saveProduct(Product product, Long categoryId) throws NotFoundException;

    public List<CategoryProductPricingDTO> fetchProductList();


    public List<CategoryProductPricingDTO> fetchProductsByCategoryId(Long categoryId) throws NotFoundException;

    public List<CategoryProductPricingDTO> fetchProductIdByCategoryId(Long categoryId, Long productId) throws NotFoundException;

    public List<CategoryProductPricingDTO> fetchByProductId(Long productId) throws NotFoundException;

    public List<CategoryProductPricingDTO> fetchByProductName(String productName) throws NotFoundException;


    public List<CategoryProductPricingDTO> fetchByProductCode(Long productCode) throws NotFoundException;


    public Message updateProduct(Long productId, Long id, Product product) throws NotFoundException;

    public Message deleteProduct(Long productId) throws NotFoundException;
}
