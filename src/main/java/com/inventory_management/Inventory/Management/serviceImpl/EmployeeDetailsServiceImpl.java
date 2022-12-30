package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.EmployeeDetailsRepository;
import com.inventory_management.Inventory.Management.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public Message saveEmployee(EmployeeDetails employeeDetails) {
        Message message = new Message();
        if (employeeDetailsRepository.existsByEmployeeCodeIgnoreCase(employeeDetails.getEmployeeCode())) {
            message.setMessage("Code exists");
            return message;
        }
        if (employeeDetailsRepository.existsByEmployeeEmailIgnoreCase(employeeDetails.getEmployeeEmail())) {
            message.setMessage("Email id exists");
            return message;
        }

        if (employeeDetailsRepository.existsByEmployeeContactIgnoreCase(employeeDetails.getEmployeeContact())) {
            message.setMessage("number exists");
            return message;
        }

        employeeDetailsRepository.save(employeeDetails);
        message.setMessage("Employee details saved");
        return message;
    }

    @Override
    public List<EmployeeDetails> fetchEmployeeList() {
        return employeeDetailsRepository.findAll();
    }

    @Override
    public Optional<EmployeeDetails> fetchEmployeeListById(Long employeeId) throws NotFoundException {

        Optional<EmployeeDetails> employeeDetails = employeeDetailsRepository.findById(employeeId);

        if (!employeeDetails.isPresent()) {
            throw new NotFoundException("Employee with this Id does not exist");
        }
        return employeeDetails;
    }
}
