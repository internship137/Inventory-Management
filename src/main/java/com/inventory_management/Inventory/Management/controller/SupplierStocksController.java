package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.dto.SupplierCategorySupplierStockDTO;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.SupplierStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierStocksController {

    @Autowired
    private SupplierStocksService supplierStocksService;

    // save
    @PostMapping("/category/{supplierCategoryId}/product")
    public SupplierStocks saveSupplierStocks(@RequestBody SupplierStocks supplierStocks,
                                             @PathVariable Long supplierCategoryId) throws NotFoundException {
        return supplierStocksService.saveSupplierStocks(supplierStocks, supplierCategoryId);
    }


    // get all products/stocks
    @GetMapping("/all-stocks")
    public List<SupplierCategorySupplierStockDTO> getAllProducts() {
        return supplierStocksService.getAllProducts();
    }


    // get products based on category
    @GetMapping("/category/{supplierCategoryId}/product")
    public List<SupplierCategorySupplierStockDTO> fetchAllProductsByCategory(@PathVariable Long supplierCategoryId) throws NotFoundException {
        return supplierStocksService.fetchAllProductsByCategory(supplierCategoryId);
    }


    // get a specific product based on category
    @GetMapping("/category/{supplierCategoryId}/product/{supplierStocksId}")
    public List<SupplierCategorySupplierStockDTO> getBySupplierCategoryIdAndSupplierStocksId(@PathVariable Long supplierCategoryId,
                                                                                           @PathVariable Long supplierStocksId) throws NotFoundException {
        return supplierStocksService.getBySupplierCategoryIdAndSupplierStocksId(supplierCategoryId, supplierStocksId);
    }


    //search a product
    @GetMapping("/all-stocks/search/{supplierProductName}")
    public List<SupplierCategorySupplierStockDTO> searchBySupplierProductName(@PathVariable String supplierProductName) throws NotFoundException {
        return supplierStocksService.searchBySupplierProductName(supplierProductName);
    }


    @DeleteMapping("/all-stocks/{supplierStocksId}")
    public String deleteSupplierProduct(@PathVariable Long supplierStocksId) throws NotFoundException {
        supplierStocksService.deleteSupplierProduct(supplierStocksId);
        return "Deleted Successfully";
    }

    @GetMapping("/all-stocks/{supplierStocksId}")
    public List<SupplierCategorySupplierStockDTO> getProductsById(@PathVariable Long supplierStocksId) throws NotFoundException {
        return supplierStocksService.getProductsById(supplierStocksId);
    }


    @PutMapping("/category/{supplierCategoryId}/product/{supplierStocksId}")
    public String updateSupplierProduct(@PathVariable Long supplierCategoryId,
                                        @PathVariable Long supplierStocksId,
                                        @RequestBody SupplierStocks supplierStocks) throws NotFoundException {
        return supplierStocksService.updateSupplierProduct(supplierCategoryId,supplierStocksId,supplierStocks);
    }

}
