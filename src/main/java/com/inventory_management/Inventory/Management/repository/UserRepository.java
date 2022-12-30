package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
