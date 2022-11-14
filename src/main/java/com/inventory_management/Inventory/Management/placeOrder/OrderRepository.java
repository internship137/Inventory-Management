package com.inventory_management.Inventory.Management.placeOrder;

import com.inventory_management.Inventory.Management.entity.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PlaceOrder,Long> {
}
