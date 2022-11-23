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
public class SupplierCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierCategoryId;

    @Column(name = "supplier_category_name")
    private String supplierCategoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "supplier_category_created_date_time")
    private Date supplierCategoryCreatedDateTime = new Date(System.currentTimeMillis());

}
