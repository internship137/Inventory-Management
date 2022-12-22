package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ReturnedProducts;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReturnedProductsService {
    Message returnDamagedProducts(Long damagedProductsId, ReturnedProducts returnedProducts) throws NotFoundException;

    List<ReturnedProducts> fetchAllReturnRequests(int pageNo);

    Optional<ReturnedProducts> fetchReturnRequestById(Long returnProductsId) throws NotFoundException;
}
