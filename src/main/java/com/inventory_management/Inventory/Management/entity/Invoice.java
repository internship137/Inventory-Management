package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    @Column(name = "selling_quantity")
    private Long sellingQuantity;

    @Column(name = "customer_email")
    private String customerEmail;

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
}
