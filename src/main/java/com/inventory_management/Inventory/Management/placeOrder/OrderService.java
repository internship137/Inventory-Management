package com.inventory_management.Inventory.Management.placeOrder;

import com.inventory_management.Inventory.Management.entity.PlaceOrder;

import java.util.List;

public interface OrderService {
    String saveOrder(PlaceOrder placeOrder, Long supplierStocksId);

    List<PlaceOrder> getAllOrders();
}
