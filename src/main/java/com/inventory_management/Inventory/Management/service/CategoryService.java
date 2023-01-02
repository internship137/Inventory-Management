package com.inventory_management.Inventory.Management.service;


import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface CategoryService {
    public Message saveCategory(Category category);
    List<Category> fetchCategoryList(int pageNo, int recordCount);

    public Category fetchCategoryById(Long categoryId) throws NotFoundException;

    public Category fetchCategoryByName(String categoryName) throws NotFoundException;

    public Message updateCategory(Long categoryId, Category product) throws NotFoundException;

}
