package com.inventory_management.Inventory.Management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private Long purchaseOrderId;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    @Column(name = "request_placed_date_time")
    private Date requestPlacedDateTime;


    //    @NotBlank(message = "Product cannot be empty")
    @Column(name = "product_name")
    private String productName;

//    @NotBlank(message = "Product category should not be empty")
//    @Column(name = "product_category")
//    private String productCategory;

    @NotNull(message = "Product code should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,70}$", message = "Please provide a valid Product Code")
    @Column(name = "product_code")
    private String productCode;


    @NotNull(message = "Product quantity cannot be empty")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
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

    @Column(name = "supplier_company")
    private String supplierCompany;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @JsonFormat(pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    @Column(name = "supplier_status_date")
    private String supplierStatusDate;
}
