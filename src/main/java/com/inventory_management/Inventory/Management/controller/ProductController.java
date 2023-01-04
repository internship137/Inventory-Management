package com.inventory_management.Inventory.Management.controller;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.service.ProductService;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.dto.ProductDTO;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;


    // Add Products to Category

    @PostMapping("/product/category/{categoryId}/supplierId/{supplierId}/addProduct")
    public Message saveProduct(@Valid @RequestBody Product product,
                               @PathVariable Long categoryId,
                               @PathVariable Long supplierId) throws NotFoundException {
        return productService.saveProduct(product, categoryId, supplierId);
    }


    // Get all products with categories (Pagination and Sorting)

    @GetMapping("/product/paginationAndSorting/{pageNo}/{recordCount}")
    public List<ProductDTO> fetchProductList(@PathVariable int pageNo,
                                             @PathVariable int recordCount) {
        return productService.fetchProductList(pageNo, recordCount);
    }


    // Get products from a specific category

    @GetMapping("/fromCategory/{categoryId}/product/{pageNo}/{recordCount}")
    public List<ProductDTO> fetchProductsByCategoryId(@PathVariable Long categoryId,
                                                      @PathVariable int pageNo,
                                                      @PathVariable int recordCount) throws NotFoundException {
        return productService.fetchProductsByCategoryId(categoryId, pageNo, recordCount);
    }


    // Get a specific product from a specific category


    @GetMapping("/specific/category/{categoryId}/product/{productId}")
    public List<ProductDTO> fetchProductIdByCategoryId(@PathVariable Long categoryId,
                                                       @PathVariable Long productId) throws NotFoundException {
        return productService.fetchProductIdByCategoryId(categoryId, productId);

    }


    // Get product by product name (containing)


    @GetMapping("/product/{productName}")
    public List<ProductDTO> fetchByProductName(@PathVariable String productName) throws NotFoundException {
        return productService.fetchByProductName(productName);
    }


    // Get a product by productId

    @GetMapping("/productId/{productId}")
    public List<ProductDTO> fetchByProductId(@PathVariable Long productId) throws NotFoundException {
        return productService.fetchByProductId(productId);
    }


    // Update a specific product in a specific category


    @PutMapping("/category/{categoryId}/updateProduct/{productId}")
    public Message updateProduct(@PathVariable Long categoryId,
                                 @PathVariable Long productId,
                                 @RequestBody @Valid Product product) throws NotFoundException {
        return productService.updateProduct(categoryId, productId, product);
    }

}

