package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.SupplierCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class SupplierCategoryController {

    @Autowired
    private SupplierCategoryService supplierCategoryService;

    @PostMapping("")
    public SupplierCategory saveCategory(@RequestBody SupplierCategory supplierCategory){
        return supplierCategoryService.saveCategory(supplierCategory);
    }


    @GetMapping("")
    public List<SupplierCategory> fetchAllCategoriesList(){
        return supplierCategoryService.fetchAllCategoriesList();
    }


    @GetMapping("/{supplierCategoryName}")
    public SupplierCategory fetchBySupplierCategoryName(@PathVariable("supplierCategoryName") String supplierCategoryName) throws NotFoundException {
        return supplierCategoryService.fetchBySupplierCategoryName(supplierCategoryName);
    }

    @GetMapping("/id/{id}")
    public SupplierCategory fetchBySupplierCategoryId(@PathVariable("supplierCategoryId") Long supplierCategoryId) throws NotFoundException {
        return supplierCategoryService.fetchBySupplierCategoryId(supplierCategoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteSupplierCategoryById(@PathVariable("id") Long supplierCategoryId) throws NotFoundException {
        supplierCategoryService.deleteSupplierCategoryById(supplierCategoryId);
        return "Deleted Successfully";
    }


    @PutMapping("/{id}")
    public SupplierCategory updateSupplierCategory(@PathVariable("id") Long supplierCategoryId,
                                                   @RequestBody SupplierCategory supplierCategory){
        return supplierCategoryService.updateSupplierCategory(supplierCategoryId,supplierCategory);
    }


}
