package com.inventory_management.Inventory.Management.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private HttpStatus status;
    private String message;


}
