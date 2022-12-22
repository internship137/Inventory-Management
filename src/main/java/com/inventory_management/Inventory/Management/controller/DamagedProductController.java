package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.DamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DamagedProductController {

    @Autowired
    private DamagedProductService damagedProductService;


    @PostMapping("product/addDamagedProduct")

    public Message addDamagedProduct(@Valid @RequestBody DamagedProducts damagedProducts)throws NotFoundException {
        return damagedProductService.addDamagedProduct(damagedProducts);
    }

    @GetMapping("damagedProducts/page/{pageNo}")
    public List<DamagedProducts> fetchAllDamagedProducts(@PathVariable int pageNo){
        return damagedProductService.fetchAllDamagedProducts(pageNo);
    }

    @GetMapping("damagedProducts/{damagedProductsId}")
    public Optional<DamagedProducts> fetchDamagedProductsById(@PathVariable Long damagedProductsId ) throws NotFoundException {
        return damagedProductService.fetchDamagedProductsById(damagedProductsId);
    }

    @PutMapping("damagedProducts/{damagedProductsId}")
    public Message updateDamagedProduct(@PathVariable("damagedProductsId")Long damagedProductsId, @RequestBody DamagedProducts damagedProducts) throws NotFoundException{
        return damagedProductService.updateDamagedProduct(damagedProductsId , damagedProducts);
    }

}
