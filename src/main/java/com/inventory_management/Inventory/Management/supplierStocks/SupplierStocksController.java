package com.inventory_management.Inventory.Management.supplierStocks;

import com.inventory_management.Inventory.Management.dto.SupplierCategorySupplierStockDTO;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierStocksController {

    @Autowired
    private SupplierStocksService supplierStocksService;

    // save
    @PostMapping("/supplierCategory/{supplierCategoryId}/supplierProduct")
    public SupplierStocks saveSupplierStocks(@RequestBody SupplierStocks supplierStocks,
                                             @PathVariable Long supplierCategoryId) throws NotFoundException {
        return supplierStocksService.saveSupplierStocks(supplierStocks, supplierCategoryId);
    }


    // get all products/stocks
    @GetMapping("/supplier-stocks")
    public List<SupplierCategorySupplierStockDTO> getAllProducts() {
        return supplierStocksService.getAllProducts();
    }


    // get products based on category
    @GetMapping("/supplierCategory/{supplierCategoryId}/supplierProduct")
    public List<SupplierCategorySupplierStockDTO> fetchAllProductsByCategory(@PathVariable Long supplierCategoryId) throws NotFoundException {
        return supplierStocksService.fetchAllProductsByCategory(supplierCategoryId);
    }


    // get a specific product based on category
    @GetMapping("/supplierCategory/{supplierCategoryId}/supplierProduct/{supplierStocksId}")
    public List<SupplierCategorySupplierStockDTO> getBySupplierCategoryIdAndSupplierStocksId(@PathVariable Long supplierCategoryId,
                                                                                           @PathVariable Long supplierStocksId) throws NotFoundException {
        return supplierStocksService.getBySupplierCategoryIdAndSupplierStocksId(supplierCategoryId, supplierStocksId);
    }


    //search a product
    @GetMapping("/supplier-stocks/search/{supplierProductName}")
    public List<SupplierCategorySupplierStockDTO> searchBySupplierProductName(@PathVariable String supplierProductName) throws NotFoundException {
        return supplierStocksService.searchBySupplierProductName(supplierProductName);
    }


    @DeleteMapping("/supplier-stocks/{supplierStocksId}")
    public String deleteSupplierProduct(@PathVariable Long supplierStocksId) throws NotFoundException {
        supplierStocksService.deleteSupplierProduct(supplierStocksId);
        return "Deleted Successfully";
    }

    @GetMapping("/supplier-stocks/{supplierStocksId}")
    public List<SupplierCategorySupplierStockDTO> getProductsById(@PathVariable Long supplierStocksId) throws NotFoundException {
        return supplierStocksService.getProductsById(supplierStocksId);
    }


    @PutMapping("/supplierCategory/{supplierCategoryId}/supplierProduct/{supplierStocksId}")
    public String updateSupplierProduct(@PathVariable Long supplierCategoryId,
                                        @PathVariable Long supplierStocksId,
                                        @RequestBody SupplierStocks supplierStocks) throws NotFoundException {
        return supplierStocksService.updateSupplierProduct(supplierCategoryId,supplierStocksId,supplierStocks);
    }

}
