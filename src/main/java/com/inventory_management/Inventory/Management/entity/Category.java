package com.inventory_management.Inventory.Management.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "Category name should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,70}$", message = "Please provide a valid Category Name")
    @Column(name = "category_name")
    private String categoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "category_created_date_time")
    private Date categoryCreatedDateTime = new Date(System.currentTimeMillis());

}