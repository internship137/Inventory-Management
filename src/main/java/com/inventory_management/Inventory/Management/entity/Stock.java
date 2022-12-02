package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor@NoArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_quantity")
    private Long stockQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date_time")
    private Date stockCreatedDateTime = new Date(System.currentTimeMillis());


    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

}