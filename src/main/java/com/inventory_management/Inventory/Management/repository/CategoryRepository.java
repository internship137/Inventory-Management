package com.inventory_management.Inventory.Management.repository;


import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    public Optional<Category> findByCategoryNameContaining(String categoryName) throws NotFoundException;
}
