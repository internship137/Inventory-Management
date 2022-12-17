package com.inventory_management.Inventory.Management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Column(name = "message")
    private String message;

}
