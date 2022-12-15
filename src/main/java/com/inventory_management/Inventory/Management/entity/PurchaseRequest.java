package com.inventory_management.Inventory.Management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_request_id")
    private Long purchaseRequestId;

    @CreationTimestamp
    @Column(name = "request_placed_date_time")
    private Date requestPlacedDateTime;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "expected_delivery_date")
    private Date expectedDeliveryDate;

    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    private String supplierEmail;
}
