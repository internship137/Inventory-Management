package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @CreationTimestamp
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @NotNull(message = "Selling Quantity should not be empty")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Please enter a valid Selling Quantity")
    @Column(name = "selling_quantity")
    @Min(0)
    @Max(9999999)
    private String sellingQuantity;

    @NotBlank(message = "Customer Email Address should not be empty")
    @Pattern(regexp = "[a-zA_Z0-9_\\-\\.]+[@][a-z]+[\\.][a-z]{2,3}",message = "Please enter a valid Email Address")
    @Column(name = "customer_email")
    private String customerEmail;

    @NotBlank(message = "Customer name should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Customer Name")
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "gst")
    private String gstSlab;

    @Column(name="sgst")
    private Long sgst;

    @Column(name="cgst")
    private Long cgst;

    @Column(name = "grand_total")
    private Long grandTotal;
}
