package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface OrderService {
    String saveOrder(PlaceOrder placeOrder, Long supplierStocksId) throws NotFoundException;

    List<PlaceOrderSupplierStocksDTO> getAllOrders() ;

    List<PlaceOrderSupplierStocksDTO> getOrderById(Long orderId) throws NotFoundException;

    void deleteOrder(Long orderId) throws NotFoundException;

    void updateOrder(Long orderId,PlaceOrder placeOrder) throws NotFoundException;
}
