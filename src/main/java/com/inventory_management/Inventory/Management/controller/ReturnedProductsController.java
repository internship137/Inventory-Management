package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ReturnedProducts;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.ReturnedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ReturnedProductsController {

    @Autowired
    private ReturnedProductsService returnedProductsService;

    @PostMapping("damagedProducts/{damagedProductsId}/return")
    public Message returnDamagedProducts(@PathVariable("damagedProductsId") Long damagedProductsId, @Valid @RequestBody ReturnedProducts returnedProducts) throws NotFoundException {
        return returnedProductsService.returnDamagedProducts(damagedProductsId, returnedProducts);
    }

    @GetMapping("returnRequest/page/{pageNo}")
    public List<ReturnedProducts> fetchAllReturnRequests(@PathVariable int pageNo) {
        return returnedProductsService.fetchAllReturnRequests(pageNo);
    }

    @GetMapping("returnRequest/{returnProductsId}")
    public Optional<ReturnedProducts> fetchReturnRequestById(@PathVariable("returnProductsId") Long returnProductsId) throws NotFoundException {
        return returnedProductsService.fetchReturnRequestById(returnProductsId);
    }
}
