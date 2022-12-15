package com.inventory_management.Inventory.Management.event.listener;

import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import com.inventory_management.Inventory.Management.event.PurchaseRequestEvent;
import com.inventory_management.Inventory.Management.service.PurchaseRequestService;
import com.inventory_management.Inventory.Management.utilities.RequestEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PurchaseRequestCompleteEventListener implements ApplicationListener<PurchaseRequestEvent> {
    @Autowired
    private PurchaseRequestService purchaseRequestService;

    @Autowired
    private RequestEmail requestEmail;

    @Override
    public void onApplicationEvent(PurchaseRequestEvent event) {

        //create verification token for the USer with link

        PurchaseRequest purchaseRequest = event.getPurchaseRequest();
        String token = UUID.randomUUID().toString();
        String reject = UUID.randomUUID().toString();

        purchaseRequestService.saveTokens(purchaseRequest, token, reject);

        //send email

        String url1 = event.getApplicationUrl1() + "/confirmOrder?token="
                + token;

        String url2 = event.getApplicationUrl2() + "/denyOrder?reject="
                + reject;

        String toEmail = purchaseRequest.getSupplierEmail();
        requestEmail.sendRequestEmail(toEmail,
                "An order for " + purchaseRequest.getProductName() + " has been requested by XYZ" + "\n"
                        + "Requested Quantity " + purchaseRequest.getProductQuantity() + "\n" + "\n" +
                        "The delivery is expected before " + purchaseRequest.getExpectedDeliveryDate()
                        + "\n" + "\n"
                        + "To approve the request CLICK HERE \n" +
                        url1 + "\n" + "\n"
                        + "To reject the request CLICK HERE \n"
                        + url2
                ,
                "Request for " + purchaseRequest.getProductName());

    }
}
