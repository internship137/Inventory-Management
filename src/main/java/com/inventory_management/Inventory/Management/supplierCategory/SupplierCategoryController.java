package com.inventory_management.Inventory.Management.supplierCategory;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/search/{supplierCategoryName}")
    public SupplierCategory fetchBySupplierCategoryName(@PathVariable("supplierCategoryName") String supplierCategoryName) throws NotFoundException {
        return supplierCategoryService.fetchBySupplierCategoryName(supplierCategoryName);
    }

    @GetMapping("/{supplierCategoryId}")
    public SupplierCategory fetchBySupplierCategoryId(@PathVariable("supplierCategoryId") Long supplierCategoryId) throws NotFoundException {
        return supplierCategoryService.fetchBySupplierCategoryId(supplierCategoryId);
    }

    @DeleteMapping("/{supplierCategoryId}")
    public String deleteSupplierCategoryById(@PathVariable("supplierCategoryId") Long supplierCategoryId) throws NotFoundException {
        supplierCategoryService.deleteSupplierCategoryById(supplierCategoryId);
        return "Deleted Successfully";
    }


    @PutMapping("/{supplierCategoryId}")
    public SupplierCategory updateSupplierCategory(@PathVariable("supplierCategoryId") Long supplierCategoryId,
                                                   @RequestBody SupplierCategory supplierCategory) throws NotFoundException {
        return supplierCategoryService.updateSupplierCategory(supplierCategoryId,supplierCategory);
    }


}
