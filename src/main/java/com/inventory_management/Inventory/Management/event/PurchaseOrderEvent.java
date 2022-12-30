package com.inventory_management.Inventory.Management.event;

import com.inventory_management.Inventory.Management.entity.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class PurchaseOrderEvent extends ApplicationEvent {

    private PurchaseOrder purchaseOrder;
    private String applicationUrl1;
    private String applicationUrl2;

    public PurchaseOrderEvent(PurchaseOrder purchaseOrder, String applicationUrl1, String applicationUrl2) {
        super(purchaseOrder);
        this.purchaseOrder = purchaseOrder;
        this.applicationUrl1 = applicationUrl1;
        this.applicationUrl2 = applicationUrl2;

    }
}
