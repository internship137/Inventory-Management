package com.inventory_management.Inventory.Management.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "category_created_date_time")
    private Date categoryCreatedDateTime = new Date(System.currentTimeMillis());


}