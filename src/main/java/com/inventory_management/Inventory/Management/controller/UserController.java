package com.inventory_management.Inventory.Management.controller;


import com.inventory_management.Inventory.Management.entity.User;
import com.inventory_management.Inventory.Management.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUser(){
        userService.initRolesAndUser();
    }


    @PostMapping("/Register")
    public User registerNewUser(@RequestBody User user)
    {
        return userService.registerNewUser(user);
    }

}
