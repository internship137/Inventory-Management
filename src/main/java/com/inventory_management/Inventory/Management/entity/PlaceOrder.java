package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @CreationTimestamp
    @Column(name = "order_placed_date_time")
    private Date orderPlacedDate;

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @Column(name = "supplier_stocks_id")
    private Long supplierStocksId;

    @Column(name = "supplier_product_name")
    private String supplierProductName;

    @Column(name = "supplier_category_name")
    private String supplierCategoryName;

    @Column(name = "supplier_product_price")
    private Long supplierProductPrice;

    @Column(name = "order_status")
    private String orderStatus;

}
