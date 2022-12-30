package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    boolean existsByEmployeeCodeIgnoreCase(String employeeCode);

    boolean existsByEmployeeEmailIgnoreCase(String employeeEmail);

    String findByEmployeeContactIgnoreCase(String valueOf);

    boolean existsByEmployeeContactIgnoreCase(String employeeContact);
}
