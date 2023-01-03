package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseOrder;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.event.PurchaseOrderEvent;
import com.inventory_management.Inventory.Management.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping("/purchaseOrder")
    public Message saveOrder(@Valid @RequestBody PurchaseOrder purchaseOrder,
                             final HttpServletRequest request) throws NotFoundException {
        PurchaseOrder purchaseOrder1 = purchaseOrderService.saveOrder(purchaseOrder);
        publisher.publishEvent(new PurchaseOrderEvent(purchaseOrder1, applicationUrl1(request), applicationUrl2(request)));
        Message message = new Message();
        message.setMessage("Request placed successfully");
        return message;
    }

    @GetMapping("/confirmOrder")
    public Message confirmOrder(@RequestParam("token") String token) {
        return purchaseOrderService.confirmOrderStatus(token);
    }

    @GetMapping("/denyOrder")
    public Message rejectOrder(@RequestParam("reject") String reject) {
        return purchaseOrderService.rejectOrder(reject);
    }

    private String applicationUrl2(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    private String applicationUrl1(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }


    @GetMapping("/purchaseOrder/page/{pageNo}")
    public List<PurchaseOrder> fetchAllRequest(@PathVariable int pageNo) {
        return purchaseOrderService.fetchAllRequest(pageNo);
    }

    @GetMapping("/purchaseOrder/{purchaseRequestId}")
    public Optional<PurchaseOrder> fetchRequestsById(@PathVariable("purchaseRequestId") Long purchaseRequestId) throws NotFoundException {
        return purchaseOrderService.fetchRequestsById(purchaseRequestId);
    }

    @GetMapping("/purchaseOrder/changeStatus/{purchaseRequestId}")
    public Message changeStatus(@PathVariable("purchaseRequestId") Long purchaseRequestId) throws NotFoundException {
        return purchaseOrderService.changeStatus(purchaseRequestId);
    }
}
