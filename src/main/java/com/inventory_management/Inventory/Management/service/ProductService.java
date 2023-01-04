package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.dto.ProductDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface ProductService {

    Message saveProduct(Product product, Long categoryId, Long supplierId) throws NotFoundException;

    public List<ProductDTO> fetchProductList(int pageNo, int recordCount);

    public List<ProductDTO> fetchProductsByCategoryId(Long categoryId, int pageNo, int recordCount) throws NotFoundException;


    public List<ProductDTO> fetchProductIdByCategoryId(Long categoryId, Long productId) throws NotFoundException;

    public List<ProductDTO> fetchByProductId(Long productId) throws NotFoundException;

    public List<ProductDTO> fetchByProductName(String productName) throws NotFoundException;

    public Message updateProduct(Long productId, Long id, Product product) throws NotFoundException;

}
