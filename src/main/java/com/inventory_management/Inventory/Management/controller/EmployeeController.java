package com.inventory_management.Inventory.Management.controller;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeDetails saveEmployee(@RequestBody EmployeeDetails employeeDetails,
                                        @PathVariable Long employeeId) throws NotFoundException {
        return employeeService.saveEmployee(employeeDetails,employeeId);
    }

    @GetMapping("/employee")
    public List<EmployeeDetails> fetchEmployeeList() {
        return employeeService.fetchEmployeeList();
    }

    @GetMapping("/employee/{employeeId}")
    public Optional<EmployeeDetails> fetchEmployeeListById(@PathVariable Long employeeId) throws NotFoundException {
        return employeeService.fetchEmployeeListById(employeeId);
    }

}
