package com.inventory_management.Inventory.Management.placeOrder;

import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import com.inventory_management.Inventory.Management.supplierStocks.SupplierStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierStocksRepository supplierStocksRepository;


    @Override
    public String saveOrder(PlaceOrder placeOrder, Long supplierStocksId) {
        SupplierStocks supplierStocks = supplierStocksRepository.findById(supplierStocksId).get();
        Long supplierQty = supplierStocks.getSupplierProductQuantity();
        Long orderQty = placeOrder.getOrderQuantity();

        if (orderQty > supplierQty) {
            return "Order quantity exceeded supplier quantity";
        }

        placeOrder.setSupplierStocks(supplierStocks);
        orderRepository.save(placeOrder);
        supplierQty=supplierQty-orderQty;
        supplierStocks.setSupplierProductQuantity(supplierQty);
        supplierStocksRepository.save(supplierStocks);
        return "Order placed successfully";
    }

    @Override
    public List<PlaceOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
