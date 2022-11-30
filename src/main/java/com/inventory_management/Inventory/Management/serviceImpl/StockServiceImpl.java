package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.dto.StocksDTO;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.entity.Stock;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.StockRepository;
import com.inventory_management.Inventory.Management.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;


    // Add Stock


    @Override
    public Stock saveStock(Long productId, Stock stock) {
        Product product = productRepository.findById(productId).get();
        stock.setProduct(product);
        return stockRepository.save(stock);
    }


    // Get all Stock


    @Override
    public List<StocksDTO> fetchStockList() {
        return stockRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get By Stock Id


    @Override
    public List<StocksDTO> fetchByStockId(Long stockId) throws NotFoundException {

        Optional<Stock> stockIdDto = stockRepository.findById(stockId);

        if (stockIdDto.isEmpty()){
            throw new NotFoundException("Stock with this Id does not exist");
        }
        return stockRepository.findById(stockId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Update Stocks


    @Override
    public String updateStock(Long productId, Long stockId, Stock stock) throws NotFoundException{

        if (!stockRepository.existsById(stockId)) {
            throw new NotFoundException("Stock with this id does not exist");
        }

        Stock stockDB = stockRepository.findStockIdUsingProductId(productId, stockId);

        if (Objects.nonNull(stock.getStockQuantity()) &&
                !"".equalsIgnoreCase(String.valueOf(stock.getStockQuantity()))) {
            stockDB.setStockQuantity(stock.getStockQuantity());
        }

        stockRepository.save(stockDB);
        return "Updated Successfully";
    }


    // Delete Stocks


    @Override
    public void deleteStock(Long stockId) throws NotFoundException{

            if (!stockRepository.existsById(stockId)) {
                throw new NotFoundException("Stock Id does not exist");
            }
            stockRepository.deleteById(stockId);

    }


    // DTO


    private StocksDTO convertEntityToDto(Stock stock) {
        StocksDTO stocksDTO = new StocksDTO();

        stocksDTO.setStockId(stock.getStockId());
        stocksDTO.setStockQuantity(stock.getStockQuantity());
        stocksDTO.setProduct(stock.getProduct().getProductName());
        stocksDTO.setCategory(stock.getProduct().getCategory().getCategoryName());
        stocksDTO.setSupplier(stock.getSupplier().getSupplierName());

        return stocksDTO;
    }
}
