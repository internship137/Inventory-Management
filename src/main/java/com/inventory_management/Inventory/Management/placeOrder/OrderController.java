package com.inventory_management.Inventory.Management.placeOrder;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/product/{supplierStocksId}/order")
    public String saveOrder(@RequestBody PlaceOrder placeOrder,
                            @PathVariable Long supplierStocksId) throws NotFoundException {
        return orderService.saveOrder(placeOrder,supplierStocksId);
    }

    @GetMapping("/all-orders")
    public List<PlaceOrderSupplierStocksDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    private List<PlaceOrderSupplierStocksDTO> getOrderById(@PathVariable Long orderId) throws NotFoundException {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/orders/delete/{orderId}")
    private String deleteOrder(@PathVariable Long orderId) throws NotFoundException {
        orderService.deleteOrder(orderId);
        return "Order Deleted Successfully";
    }

    @PutMapping("/orders/{orderId}")
    private String updateOrder(@PathVariable Long orderId,
                               @RequestBody PlaceOrder placeOrder) throws NotFoundException {
        orderService.updateOrder(orderId,placeOrder);
        return "Updated Successfully";
    }
}
