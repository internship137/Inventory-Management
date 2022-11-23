package com.inventory_management.Inventory.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierStocksId;

    @Column(name = "supplier_product_name")
    private String supplierProductName;

    @ManyToOne
    @JoinColumn(name = "supplierCategoryId")
    private SupplierCategory supplierCategory;

    @Column(name = "supplier_product_price")
    private Long supplierProductPrice;

    @Column(name = "supplier_product_quantity")
    private Long supplierProductQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "supplier_stocks_created_date_time")
    private Date supplierStocksCreatedDateTime = new Date(System.currentTimeMillis());

}
