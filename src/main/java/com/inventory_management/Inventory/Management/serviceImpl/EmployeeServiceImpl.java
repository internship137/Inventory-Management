package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.EmployeeRepository;
import com.inventory_management.Inventory.Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDetails saveEmployee(EmployeeDetails employeeDetails, Long employeeId) {
        return employeeRepository.save(employeeDetails);
    }

    @Override
    public List<EmployeeDetails> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeDetails> fetchEmployeeListById(Long employeeId) throws NotFoundException {

        Optional<EmployeeDetails> employeeDetails=employeeRepository.findById(employeeId);

        if (!employeeDetails.isPresent()) {
            throw new NotFoundException("Employee with this Id does not exist");
        }
        return employeeDetails;
    }
}
