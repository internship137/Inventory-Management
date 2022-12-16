package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.event.PurchaseRequestEvent;
import com.inventory_management.Inventory.Management.service.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseRequestController {
    @Autowired
    private PurchaseRequestService purchaseRequestService;

    @Autowired
    private ApplicationEventPublisher publisher;



    @PostMapping("supplier/{supplierId}/request")
    public Message saveOrder(@Valid @RequestBody PurchaseRequest purchaseRequest,
                             @PathVariable("supplierId") Long supplierId,
                             final HttpServletRequest request) {
        PurchaseRequest purchaseRequest1 = purchaseRequestService.saveOrder(purchaseRequest,supplierId);
        publisher.publishEvent(new PurchaseRequestEvent(purchaseRequest1,applicationUrl1(request),applicationUrl2(request)));
        Message message=new Message();
        message.setMessage("Request placed successfully");
        return message;
    }

    @GetMapping("/confirmOrder")
    public Message confirmOrder(@RequestParam("token") String token) {
        return purchaseRequestService.confirmOrderStatus(token);
//        String result = purchaseRequestService.confirmOrderStatus(token);
//        if (result.equalsIgnoreCase("valid")) {
//            return "Confirmed";
//        }
//        return result;
    }

    @GetMapping("/denyOrder")
    public Message rejectOrder(@RequestParam("reject") String reject) {
        return purchaseRequestService.rejectOrder(reject);
//        String result = purchaseRequestService.rejectOrder(reject);
//        if (result.equalsIgnoreCase("valid")) {
//            return "Confirmed";
//        }
//        return result;
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


    @GetMapping("/allRequests")
    public List<PurchaseRequest> fetchAllRequest(){
        return purchaseRequestService.fetchAllRequest();
    }

    @GetMapping("/allRequests/{purchaseRequestId}")
    public Optional<PurchaseRequest> fetchRequestsById(@PathVariable("purchaseRequestId") Long purchaseRequestId) throws NotFoundException {
        return purchaseRequestService.fetchRequestsById(purchaseRequestId);
    }

}
