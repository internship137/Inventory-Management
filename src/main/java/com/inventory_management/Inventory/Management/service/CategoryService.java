package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category saveCategory(Category category);

    public List<Category> fetchCategoryList();

    public Category fetchCategoryById(Long categoryId) throws NotFoundException;

    public Category fetchCategoryByName(String categoryName) throws NotFoundException;


    public Category updateCategory(Long categoryId, Category product) throws NotFoundException;


}
