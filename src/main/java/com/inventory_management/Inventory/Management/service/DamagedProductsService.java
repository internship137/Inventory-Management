package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface DamagedProductsService {
    List<DamagedProducts> fetchAllDamagedProducts(int pageNo);

    Optional<DamagedProducts> fetchDamagedProductById(Long damagedProductsId) throws NotFoundException;

    Object fetchAllDamagedProductsToReturn();
}
