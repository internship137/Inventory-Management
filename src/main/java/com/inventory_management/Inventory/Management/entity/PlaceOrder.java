package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_placed_date_time")
    private Date orderPlacedDateTime = new Date(System.currentTimeMillis());

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @ManyToOne
    @JoinColumn(name = "supplierStocksId")
    private SupplierStocks supplierStocks;

}
