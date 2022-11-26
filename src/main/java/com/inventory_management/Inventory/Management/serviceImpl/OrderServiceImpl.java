package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.dto.PlaceOrderSupplierStocksDTO;
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
    public String saveOrder(PlaceOrder placeOrder, Long supplierStocksId) throws NotFoundException {
        if (!supplierStocksRepository.existsById(supplierStocksId)){
            throw new NotFoundException("Product with this ID does not exist");
        }

        SupplierStocks supplierStocks = supplierStocksRepository.findById(supplierStocksId).get();

        Long supplierStockId= supplierStocks.getSupplierStocksId();
        String supplierProductName1=supplierStocks.getSupplierProductName();
        String stockCategory= supplierStocks.getSupplierCategory().getSupplierCategoryName();
        Long orderPrice=supplierStocks.getSupplierProductPrice();

        Long supplierQty = supplierStocks.getSupplierProductQuantity();
        Long orderQty = placeOrder.getOrderQuantity();

        if (orderQty > supplierQty) {
            return "Order quantity exceeded supplier quantity";
        }

        placeOrder.setSupplierCategoryName(stockCategory);
        placeOrder.setSupplierStocksId(supplierStockId);
        placeOrder.setSupplierProductName(supplierProductName1);
        placeOrder.setSupplierProductPrice(orderPrice);

        orderRepository.save(placeOrder);
        supplierQty = supplierQty - orderQty;
        supplierStocks.setSupplierProductQuantity(supplierQty);
        supplierStocksRepository.save(supplierStocks);

        orderSuccessfulEmail.sendOrderSuccessfulEmail(
                "gokuldas1999@gmail.com",
                "Your Order for "+placeOrder.getSupplierProductName()+" has been placed successfully \n"+
                "Order Id: "+placeOrder.getOrderId()+"\n"+
                "Quantity: "+placeOrder.getOrderQuantity()+"\n"+
                "Order Price: "+placeOrder.getSupplierProductPrice()+"\n"+
                "Order Placed On: "+placeOrder.getOrderPlacedDate(),
                "Order Placed Successfully"
        );

        return "Order placed successfully";
    }

    @Override
    public List<PlaceOrderSupplierStocksDTO> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceOrderSupplierStocksDTO> getOrderById(Long orderId) throws NotFoundException {
        if (!orderRepository.existsById(orderId)){
            throw new NotFoundException("Order with this id does not exist");
        }
        return orderRepository.findById(orderId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }



    @Override
    public void updateOrder(Long orderId,PlaceOrder placeOrder) throws NotFoundException{
        PlaceOrder order=orderRepository.findById(orderId).get();

        if (!orderRepository.existsById(orderId)){
            throw new NotFoundException("Order with this id does not exist");
        }

        if(Objects.nonNull(placeOrder.getOrderQuantity())&&
                !"".equalsIgnoreCase(String.valueOf(placeOrder.getOrderQuantity()))){
            order.setOrderQuantity(placeOrder.getOrderQuantity());
        }
        orderRepository.save(order);
    }


    @Override
    public void deleteOrder(Long orderId) throws NotFoundException {
        if (!orderRepository.existsById(orderId)){
            throw new NotFoundException("Order with this id does not exist");
        }
        orderRepository.deleteById(orderId);
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
        return placeOrderSupplierStocksDTO;
    }
}
