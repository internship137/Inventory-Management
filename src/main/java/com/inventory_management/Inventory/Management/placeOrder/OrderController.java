package com.inventory_management.Inventory.Management.placeOrder;

import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/product/{supplierStocksId}/order")
    public String saveOrder(@RequestBody PlaceOrder placeOrder,
                            @PathVariable Long supplierStocksId){
        return orderService.saveOrder(placeOrder,supplierStocksId);
    }

    @GetMapping("/all-orders")
    public List<PlaceOrder> getAllOrders(){
        return orderService.getAllOrders();
    }
}
