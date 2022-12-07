package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.utilities.OrderSuccessfulEmail;
import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.OrderRepository;
import com.inventory_management.Inventory.Management.service.OrderService;
import com.inventory_management.Inventory.Management.repository.SupplierStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierStocksRepository supplierStocksRepository;

    @Autowired
    private OrderSuccessfulEmail orderSuccessfulEmail;


    @Override
    public Message saveOrder(PlaceOrder placeOrder, Long supplierStocksId) throws NotFoundException {
        if (!supplierStocksRepository.existsById(supplierStocksId)) {
            throw new NotFoundException("Product with this ID does not exist");
        }

        SupplierStocks supplierStocks = supplierStocksRepository.findById(supplierStocksId).get();

        Long supplierStockId = supplierStocks.getSupplierStocksId();
        String supplierProductName1 = supplierStocks.getSupplierProductName();
        String stockCategory = supplierStocks.getSupplierCategory().getSupplierCategoryName();
        Long orderPrice = supplierStocks.getSupplierProductPrice();

        Long supplierQty = supplierStocks.getSupplierProductQuantity();
        Long orderQty = placeOrder.getOrderQuantity();


        if (orderQty > supplierQty) {
            Message message = new Message();
            message.setMessage("Order quantity exceeded supplier quantity");
            return message;
        }

        placeOrder.setSupplierCategoryName(stockCategory);
        placeOrder.setSupplierStocksId(supplierStockId);
        placeOrder.setSupplierProductName(supplierProductName1);
        placeOrder.setSupplierProductPrice(orderPrice);
        placeOrder.setOrderStatus("in-transit");

        orderRepository.save(placeOrder);
        supplierQty = supplierQty - orderQty;
        supplierStocks.setSupplierProductQuantity(supplierQty);
        supplierStocksRepository.save(supplierStocks);

        orderSuccessfulEmail.sendOrderSuccessfulEmail(
                "gokuldas1999@gmail.com",
                "Your Order for " + placeOrder.getSupplierProductName() + " has been placed successfully \n" +
                        "Order Id: " + placeOrder.getOrderId() + "\n" +
                        "Quantity: " + placeOrder.getOrderQuantity() + "\n" +
                        "Order Price: " + placeOrder.getSupplierProductPrice() + "\n" +
                        "Order Placed On: " + placeOrder.getOrderPlacedDate(),
                "Order Placed Successfully"
        );

        Message message = new Message();
        message.setMessage("Order placed successfully");
        return message;
    }

    @Override
    public List<PlaceOrderSupplierStocksDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceOrderSupplierStocksDTO> getOrderById(Long orderId) throws NotFoundException {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order with this id does not exist");
        }
        return orderRepository.findById(orderId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public Message updateOrder( PlaceOrder placeOrder, Long supplierStocksId,Long orderId)
            throws NotFoundException {
        if (!supplierStocksRepository.existsById(supplierStocksId)) {
            throw new NotFoundException("Product with this ID does not exist");
        }
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order with this id does not exist");
        }

        SupplierStocks supplierStocks = supplierStocksRepository.findById(supplierStocksId).get();
        PlaceOrder order = orderRepository.findById(orderId).get();
        Long supplierQty = supplierStocks.getSupplierProductQuantity();



        if (placeOrder.getOrderQuantity() > supplierQty) {
            Message message = new Message();
            message.setMessage("Order quantity exceeded supplier quantity");
            return message;
        }
        order.setOrderQuantity(placeOrder.getOrderQuantity());

        orderRepository.save(order);
        Message message = new Message();
        message.setMessage("successfully updated");
        return message;
    }

    @Override
    public List<PlaceOrderSupplierStocksDTO> getByOrderId(Long orderId) throws NotFoundException {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order with this id does not exist");
        }
        return orderRepository.findById(orderId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    private PlaceOrderSupplierStocksDTO convertEntityToDto(PlaceOrder placeOrder) {
        PlaceOrderSupplierStocksDTO placeOrderSupplierStocksDTO =
                new PlaceOrderSupplierStocksDTO();


        placeOrderSupplierStocksDTO.setOrderId(placeOrder.getOrderId());
        placeOrderSupplierStocksDTO.setSupplierProductName(placeOrder.getSupplierProductName());
        placeOrderSupplierStocksDTO.setSupplierCategoryName(placeOrder.getSupplierCategoryName());
        placeOrderSupplierStocksDTO.setSupplierProductPrice(placeOrder.getSupplierProductPrice());
        placeOrderSupplierStocksDTO.setOrderQuantity(placeOrder.getOrderQuantity());
        placeOrderSupplierStocksDTO.setOrderPlacedDate(placeOrder.getOrderPlacedDate());
        placeOrderSupplierStocksDTO.setOrderStatus(placeOrder.getOrderStatus());
        return placeOrderSupplierStocksDTO;
    }
}
