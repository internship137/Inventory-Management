package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
