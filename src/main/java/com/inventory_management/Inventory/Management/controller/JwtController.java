package com.inventory_management.Inventory.Management.controller;


import com.inventory_management.Inventory.Management.entity.JwtRequest;
import com.inventory_management.Inventory.Management.entity.JwtResponse;
import com.inventory_management.Inventory.Management.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;


    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}
