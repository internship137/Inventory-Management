package com.inventory_management.Inventory.Management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Role {

    @Id
    private String roleName;
    private String roleDescription;
}
