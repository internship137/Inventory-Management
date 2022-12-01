package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface SupplierCategoryService {
    SupplierCategory saveCategory(SupplierCategory supplierCategory);

    List<SupplierCategory> fetchAllCategoriesList();

    SupplierCategory fetchBySupplierCategoryName(String supplierCategoryName) throws NotFoundException;

    SupplierCategory fetchBySupplierCategoryId(Long supplierCategoryId) throws NotFoundException;

    void deleteSupplierCategoryById(Long supplierCategoryId) throws NotFoundException;

    Message updateSupplierCategory(Long supplierCategoryId, SupplierCategory supplierCategory) throws NotFoundException;
}
