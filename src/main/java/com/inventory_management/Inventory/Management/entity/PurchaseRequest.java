package com.inventory_management.Inventory.Management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date requestPlacedDateTime;



    @NotBlank(message = "Product cannot be empty")
    @Column(name = "product_name")
    private String productName;


    @NotNull(message = "Product cannot be empty")
    @Min(value = 1,message = "Quantity must be greater than or equal to 1")
    @Column(name = "product_quantity")
    private Long productQuantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "expected_delivery_date")
    @NotNull(message = "An expected delivery date must be added")
    @Future(message = "Enter a valid date")
    private Date expectedDeliveryDate;


    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_status_date")
    private String supplierStatusDate;
}
