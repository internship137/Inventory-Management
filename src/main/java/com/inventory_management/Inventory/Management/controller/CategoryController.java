package com.inventory_management.Inventory.Management.controller;



import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.service.CategoryService;
import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // saveCategory

    @PostMapping("/category")
    public Message saveCategory(@RequestBody @Valid Category category) {
        return categoryService.saveCategory(category);

    }

    // fetchCategoryList

    @GetMapping("/category")
    public List<Category> fetchCategoryList() {
        return categoryService.fetchCategoryList();
    }

    // fetchCategoryById

    @GetMapping("/category/{categoryId}")
    public Category fetchCategoryById(@PathVariable("categoryId") Long categoryId) throws NotFoundException {
        return categoryService.fetchCategoryById(categoryId);
    }

    // fetchCategoryByName

    @GetMapping("/category/name/{name}")
    public Category fetchCategoryByName(@PathVariable("name") String categoryName) throws NotFoundException{
        return categoryService.fetchCategoryByName(categoryName);
    }

    // updateCategory

    @PutMapping("/category/{categoryId}")
    public Message updateCategory( @PathVariable ("categoryId") Long categoryId, @RequestBody @Valid Category product) throws NotFoundException{
        return categoryService.updateCategory(categoryId, product);
    }


}
