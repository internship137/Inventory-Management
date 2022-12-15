package com.inventory_management.Inventory.Management.event;

import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class PurchaseRequestEvent extends ApplicationEvent {

    private PurchaseRequest purchaseRequest;
    private String applicationUrl1;
    private String applicationUrl2;
    public PurchaseRequestEvent(PurchaseRequest purchaseRequest, String applicationUrl1, String applicationUrl2) {
        super(purchaseRequest);
        this.purchaseRequest = purchaseRequest;
        this.applicationUrl1=applicationUrl1;
        this.applicationUrl2=applicationUrl2;

    }
}
