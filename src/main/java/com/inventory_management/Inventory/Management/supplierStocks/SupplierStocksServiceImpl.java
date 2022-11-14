package com.inventory_management.Inventory.Management.supplierStocks;

import com.inventory_management.Inventory.Management.dto.SupplierCategorySupplierStockDTO;
import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.supplierCategory.SupplierCategoryRepository;
import com.inventory_management.Inventory.Management.supplierStocks.SupplierStocksRepository;
import com.inventory_management.Inventory.Management.supplierStocks.SupplierStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupplierStocksServiceImpl implements SupplierStocksService {

    @Autowired
    private SupplierStocksRepository supplierStocksRepository;

    @Autowired
    private SupplierCategoryRepository supplierCategoryRepository;

    @Override
    public SupplierStocks saveSupplierStocks(SupplierStocks supplierStocks, Long supplierCategoryId) throws NotFoundException {
        if (!supplierCategoryRepository.existsById(supplierCategoryId)) {
            throw new NotFoundException("Invalid category id provided");
        }
        SupplierCategory supplierCategory = supplierCategoryRepository.findById(supplierCategoryId).get();
        supplierStocks.setSupplierCategory(supplierCategory);
        return supplierStocksRepository.save(supplierStocks);
    }

    // fetch all products
    @Override
    public List<SupplierCategorySupplierStockDTO> getAllProducts() {
        return supplierStocksRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    //    All products based on categoryId
    @Override
    public List<SupplierCategorySupplierStockDTO> fetchAllProductsByCategory(Long supplierCategoryId) throws NotFoundException {
        List<SupplierStocks> supplierStocksDto =
                supplierStocksRepository.findBySupplierCategoryId(supplierCategoryId);

        if (supplierStocksDto.isEmpty()) {
            throw new NotFoundException("Product with this Category id does not exist");
        }
        return supplierStocksDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // get a specific product based on category
    @Override
    public List<SupplierCategorySupplierStockDTO> getBySupplierCategoryIdAndSupplierStocksId
    (Long supplierCategoryId, Long supplierStocksId) throws NotFoundException {
        List<SupplierStocks> stocksDto =
                supplierStocksRepository.findBySupplierCategoryIdAndSupplierStocksId(supplierCategoryId, supplierStocksId);

        if (stocksDto.isEmpty()) {
            throw new NotFoundException("Product with this category or product id does not exist");
        }
        return stocksDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    //search a product
    @Override
    public List<SupplierCategorySupplierStockDTO> searchBySupplierProductName(String supplierProductName) throws NotFoundException {
        List<SupplierStocks> searchStock =
                supplierStocksRepository.findBySupplierProductNameContaining(supplierProductName);
        if (searchStock.isEmpty()) {
            throw new NotFoundException("Product with this name does not exist");

        }
        return searchStock
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSupplierProduct(Long supplierStocksId) throws NotFoundException {

        if (!supplierStocksRepository.existsById(supplierStocksId)) {
            throw new NotFoundException("Invalid Id provided");
        }
        supplierStocksRepository.deleteById(supplierStocksId);
    }

    @Override
    public List<SupplierCategorySupplierStockDTO> getProductsById(Long supplierStocksId) throws NotFoundException {

        if (!supplierStocksRepository.existsById(supplierStocksId)) {
            throw new NotFoundException("Invalid product id provided");
        }
        return supplierStocksRepository.findById(supplierStocksId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String updateSupplierProduct(Long supplierCategoryId, Long supplierStocksId, SupplierStocks supplierStocks) throws NotFoundException {
        if (!supplierStocksRepository.existsById(supplierStocksId)) {
            throw new NotFoundException("Product id or category id does not exist");
        }
        SupplierStocks supplierStocks1 = supplierStocksRepository.findByCategoryAndSupplierId
                (supplierCategoryId, supplierStocksId);

        if (Objects.nonNull(supplierStocks.getSupplierProductName()) &&
                !"".equalsIgnoreCase(supplierStocks.getSupplierProductName())) {
            supplierStocks1.setSupplierProductName(supplierStocks.getSupplierProductName());
        }

        if (Objects.nonNull(supplierStocks.getSupplierProductPrice()) &&
                !"".equalsIgnoreCase(String.valueOf(supplierStocks.getSupplierProductPrice()))) {
            supplierStocks1.setSupplierProductPrice(supplierStocks.getSupplierProductPrice());
        }

        if (Objects.nonNull(supplierStocks.getSupplierProductQuantity()) &&
                !"".equalsIgnoreCase(String.valueOf(supplierStocks.getSupplierProductQuantity()))) {
            supplierStocks1.setSupplierProductQuantity(supplierStocks.getSupplierProductQuantity());
        }
        supplierStocksRepository.save(supplierStocks1);
        return "successfully updated";
    }


    //        D T O
    private SupplierCategorySupplierStockDTO convertEntityToDto(SupplierStocks supplierStocks) {
        SupplierCategorySupplierStockDTO supplierCategorySupplierStockDTO =
                new SupplierCategorySupplierStockDTO();

        supplierCategorySupplierStockDTO.setSupplierStocksId(supplierStocks.getSupplierStocksId());
        supplierCategorySupplierStockDTO.setSupplierProductName(supplierStocks.getSupplierProductName());
        supplierCategorySupplierStockDTO.setSupplierCategory(supplierStocks.getSupplierCategory().getSupplierCategoryName());
        supplierCategorySupplierStockDTO.setSupplierProductPrice(supplierStocks.getSupplierProductPrice());
        supplierCategorySupplierStockDTO.setSupplierProductQuantity(supplierStocks.getSupplierProductQuantity());
        return supplierCategorySupplierStockDTO;
    }


}
