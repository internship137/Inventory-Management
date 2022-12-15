package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails,Long> {

}
