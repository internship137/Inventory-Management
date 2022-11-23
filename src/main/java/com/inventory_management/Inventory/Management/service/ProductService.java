package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.dto.CategoryProductDTO;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product, Long categoryId);

    public List<CategoryProductDTO> fetchProductList();


    public List<CategoryProductDTO> fetchProductsByCategoryId(Long categoryId) throws NotFoundException;

    public List<CategoryProductDTO> fetchProductIdByCategoryId(Long categoryId, Long productId) throws NotFoundException;

    public List<CategoryProductDTO> fetchByProductId(Long productId) throws NotFoundException;

    public List<CategoryProductDTO> fetchByProductName(String productName) throws NotFoundException;


    public List<CategoryProductDTO> fetchByProductCode(Long productCode) throws NotFoundException;


    public String updateProduct(Long productId, Long id, Product product) ;

    public void deleteProduct(Long productId) throws NotFoundException;
}
