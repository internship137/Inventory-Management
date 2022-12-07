package com.inventory_management.Inventory.Management.model;


import lombok.Data;

@Data
public class PasswordModel {

    private String email;
    private String oldPassword;
    private String newPassword;

}
