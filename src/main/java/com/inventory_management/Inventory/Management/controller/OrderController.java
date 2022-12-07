package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/supplierProduct/{supplierStocksId}/order")
    public Message saveOrder(@RequestBody PlaceOrder placeOrder,
                             @PathVariable Long supplierStocksId) throws NotFoundException {
        return orderService.saveOrder(placeOrder, supplierStocksId);
    }

    @GetMapping("/all-orders")
    public List<PlaceOrderSupplierStocksDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    private List<PlaceOrderSupplierStocksDTO> getOrderById(@PathVariable Long orderId) throws NotFoundException {
        return orderService.getOrderById(orderId);


    }

    @PutMapping("/supplierProduct/{supplierStocksId}/order/{orderId}")
    private Message updateOrder(@RequestBody PlaceOrder placeOrder,
                                @PathVariable Long supplierStocksId,
                                @PathVariable Long orderId) throws NotFoundException {
        return orderService.updateOrder(placeOrder,supplierStocksId,orderId);
    }


}
