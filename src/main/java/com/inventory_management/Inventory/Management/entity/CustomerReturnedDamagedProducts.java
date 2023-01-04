package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReturnedDamagedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_returned_products_id")
    private Long CustomerReturnedProductsId;

    @Column(name = "invoice_id")
    private Long invoiceId;


    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_name")
    private String customerName;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Pattern(regexp = "^[0-9]{1,8}$", message = "enter a valid quantity")
    @Min(value = 1,message = "damaged quantity cannot be less than One")
    @Column(name = "customer_return_quantity")
    @NotBlank(message = "cannot be blank")
    private String customerReturnQuantity;
}
