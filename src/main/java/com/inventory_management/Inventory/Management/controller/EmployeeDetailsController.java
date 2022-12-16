package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @PostMapping("/employee")
    public Message saveEmployee(@Valid @RequestBody EmployeeDetails employeeDetails) {
        return employeeDetailsService.saveEmployee(employeeDetails);
    }

    @GetMapping("/employee")
    public List<EmployeeDetails> fetchEmployeeList() {
        return employeeDetailsService.fetchEmployeeList();
    }

    @GetMapping("/employee/{employeeId}")
    public Optional<EmployeeDetails> fetchEmployeeListById(@PathVariable Long employeeId) throws NotFoundException {
        return employeeDetailsService.fetchEmployeeListById(employeeId);
    }

}
