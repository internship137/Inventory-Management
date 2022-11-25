package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.repository.StockRepository;
import com.inventory_management.Inventory.Management.service.StockService;
import com.inventory_management.Inventory.Management.dto.StocksDTO;
import com.inventory_management.Inventory.Management.entity.Stock;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    // Add Stock


    @PostMapping("/product/{productId}/addStock")
    public Stock saveStock(@PathVariable Long productId,
                           @RequestBody Stock stock) {
        return stockService.saveStock(productId, stock);
    }

    // Get all Stock

    @GetMapping("/stock")
    public List<StocksDTO> fetchStockList(){
        return stockService.fetchStockList();
    }

    // Get stock by Id

    @GetMapping("/stock/{stockId}")
    public List<StocksDTO> fetchByStockId(@PathVariable Long stockId) throws NotFoundException {
        return stockService.fetchByStockId(stockId);
    }


    // Get stocks of products of a category

    // Update stock

    @PutMapping("/product/{productId}/updateStocks/{stockId}")
    public String updateStock(@PathVariable Long productId,
                                @PathVariable Long stockId,
                                @RequestBody Stock stock) throws NotFoundException{
        return stockService.updateStock(productId, stockId, stock);
    }


    // Delete stock

    @DeleteMapping("/stocks/delete/{stockId}")
    public String deleteStockById(@PathVariable Long stockId) throws NotFoundException {
        stockService.deleteStock(stockId);
        return "Stock Deleted !";
    }
}
