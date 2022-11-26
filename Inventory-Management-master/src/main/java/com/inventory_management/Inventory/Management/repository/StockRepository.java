package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query(
            value = "select * from stock s where s.product_id=?1 and s.stock_id=?2",
            nativeQuery = true
    )

    Stock findStockIdUsingProductId(Long productId, Long stockId);
}
