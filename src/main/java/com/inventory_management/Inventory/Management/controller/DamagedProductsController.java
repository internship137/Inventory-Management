package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.DamagedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DamagedProductsController {

    @Autowired
    private DamagedProductsService damagedProductsService;

    @GetMapping("/allDamagedProducts/page/{pageNo}")
    public List<DamagedProducts> fetchAllDamagedProducts(@PathVariable int pageNo) {
        return damagedProductsService.fetchAllDamagedProducts(pageNo);
    }

    @GetMapping("/allDamagedProducts/{damagedProductsId}")
    public Optional<DamagedProducts> fetchDamagedProductById(@PathVariable Long damagedProductsId) throws NotFoundException {
        return damagedProductsService.fetchDamagedProductById(damagedProductsId);
    }

    @GetMapping("/toReturnProducts")
    public Object fetchAllDamagedProductsToReturn(){
        return damagedProductsService.fetchAllDamagedProductsToReturn();
    }
}
