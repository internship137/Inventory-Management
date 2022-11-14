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

    @Temporal(TemporalType.DATE)
    private Date orderPlacedDate;

    @Temporal(TemporalType.TIME)
    private Date orderPlacedTime;

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @ManyToOne
    @JoinColumn(name = "supplierStocksId")
    private SupplierStocks supplierStocks;

}
