package com.inventory_management.Inventory.Management.repository;


import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSellingPriceRepository extends JpaRepository<ProductSellingPrice, Long> {


//    @Query(
//            value = "select * from product x where x.product_id=?1 and x.selling_price_id=?2",
//            nativeQuery = true
//    )
//    public ProductSellingPrice findSellingPriceIdByProductId(Long productId, Long sellingPriceId);


    public List<ProductSellingPrice> findBySellingPrice(Long sellingPrice);
}
