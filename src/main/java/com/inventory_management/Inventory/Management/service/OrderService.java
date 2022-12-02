package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;

public interface OrderService {
    Message saveOrder(PlaceOrder placeOrder, Long supplierStocksId) throws NotFoundException;

    List<PlaceOrderSupplierStocksDTO> getAllOrders() ;

    List<PlaceOrderSupplierStocksDTO> getOrderById(Long orderId) throws NotFoundException;


    Message updateOrder(Long orderId, PlaceOrder placeOrder) throws NotFoundException;

    List<PlaceOrderSupplierStocksDTO> getByOrderId(Long orderId) throws NotFoundException;
}
