package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeDetailsService {
    Message saveEmployee(EmployeeDetails employeeDetails);

    List<EmployeeDetails> fetchEmployeeList();

    Optional<EmployeeDetails> fetchEmployeeListById(Long employeeId) throws NotFoundException;
}
