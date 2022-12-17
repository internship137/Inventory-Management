package com.inventory_management.Inventory.Management.controller;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.service.ProductService;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.dto.CategoryProductPricingDTO;
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

    @PostMapping("/product/category/{categoryId}/addProduct")
    public Message saveProduct(@Valid @RequestBody  Product product,
                               @PathVariable Long categoryId) throws NotFoundException{
        return productService.saveProduct(product, categoryId);
    }


    // Get all products with categoriesa

    @GetMapping("/product")
    public List<CategoryProductPricingDTO> fetchProductList() {
        return productService.fetchProductList();
    }


    // Get products from a specific category

    @GetMapping("/fromCategory/{categoryId}/product")
    public List<CategoryProductPricingDTO> fetchProductsByCategoryId(@PathVariable Long categoryId) throws NotFoundException {
        return productService.fetchProductsByCategoryId(categoryId);
    }

    // Get a specific product from a specific category


    @GetMapping("/specific/category/{categoryId}/product/{productId}")
    public List<CategoryProductPricingDTO> fetchProductIdByCategoryId(@PathVariable Long categoryId,
                                                                      @PathVariable Long productId) throws NotFoundException {
        return productService.fetchProductIdByCategoryId(categoryId, productId);

    }


    // Get product by product name (containing)


    @GetMapping("/product/{productName}")
    public List<CategoryProductPricingDTO> fetchByProductName(@PathVariable String productName) throws NotFoundException {
        return productService.fetchByProductName(productName);
    }

    // Get a product by productId

    @GetMapping("/productId/{productId}")
    public List<CategoryProductPricingDTO> fetchByProductId(@PathVariable Long productId) throws NotFoundException {
        return productService.fetchByProductId(productId);
    }


    // Update a specific product in a specific category


    @PutMapping("/category/{categoryId}/updateProduct/{productId}")
    public Message updateProduct(@PathVariable Long categoryId,
                                 @PathVariable Long productId,
                                 @RequestBody @Valid Product product) throws NotFoundException{
        return productService.updateProduct(categoryId, productId, product);
    }


    // Delete a product


    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable Long productId) throws NotFoundException {
        productService.deleteProduct(productId);
        return "Product Deleted !";
    }
}

