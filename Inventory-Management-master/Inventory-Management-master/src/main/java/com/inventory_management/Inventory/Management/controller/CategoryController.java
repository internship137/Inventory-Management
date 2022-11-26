package com.inventory_management.Inventory.Management.controller;



import com.inventory_management.Inventory.Management.service.CategoryService;
import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // saveCategory

    @PostMapping("")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);

    }

    // fetchCategoryList

    @GetMapping("")
    public List<Category> fetchCategoryList() {
        return categoryService.fetchCategoryList();
    }

    // fetchCategoryById

    @GetMapping("/{categoryId}")
    public Category fetchCategoryById(@PathVariable("categoryId") Long categoryId) throws NotFoundException {
        return categoryService.fetchCategoryById(categoryId);
    }

    // fetchCategoryByName

    @GetMapping("/name/{name}")
    public Category fetchCategoryByName(@PathVariable("name") String categoryName) throws NotFoundException{
        return categoryService.fetchCategoryByName(categoryName);
    }

    // updateCategory

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category product) throws NotFoundException{
        return categoryService.updateCategory(categoryId, product);
    }

    // deleteCategory

    @DeleteMapping("/{categoryId}")
    public String deleteCategoryById(@PathVariable("categoryId") Long categoryId) throws NotFoundException{
        categoryService.deleteCategoryById(categoryId);
        return "Category deleted Successfully !!";

    }


}
