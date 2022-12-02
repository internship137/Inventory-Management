package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.StocksDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Stock;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface StockService {
    public Stock saveStock(Long productId, Stock stock);

    public List<StocksDTO> fetchStockList();

    public List<StocksDTO> fetchByStockId(Long stockId) throws NotFoundException;


    public Message updateStock(Long productId, Long stockId, Stock stock) throws NotFoundException;

    public Message deleteStock(Long stockId) throws NotFoundException;
}
