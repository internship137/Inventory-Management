package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface DamagedProductService {
    Message addDamagedProduct(DamagedProducts damagedProducts) throws NotFoundException;

    List<DamagedProducts> fetchAllDamagedProducts(int pageNo);

    Optional<DamagedProducts> fetchDamagedProductsById(Long damagedProductsId) throws NotFoundException;

    Message updateDamagedProduct(Long damagedProductsId, DamagedProducts damagedProducts) throws NotFoundException;
}
