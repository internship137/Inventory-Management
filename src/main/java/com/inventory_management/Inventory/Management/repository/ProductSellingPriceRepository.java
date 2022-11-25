package com.inventory_management.Inventory.Management.repository;


import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSellingPriceRepository extends JpaRepository<ProductSellingPrice, Long> {



    public List<ProductSellingPrice> findBySellingPrice(Long sellingPrice);
}
