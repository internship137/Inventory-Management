package com.inventory_management.Inventory.Management.supplierStocks;

import com.inventory_management.Inventory.Management.entity.SupplierStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierStocksRepository extends JpaRepository<SupplierStocks,Long> {

    @Query(
            value = "select * from supplier_stocks s where s.supplier_category_id=?1",
            nativeQuery = true
    )
    List<SupplierStocks> findBySupplierCategoryId(Long supplierCategoryId);


    @Query(
            value = "select * from supplier_stocks s where s.supplier_category_id=?1 and s.supplier_stocks_id=?2",
            nativeQuery = true
    )
    List<SupplierStocks> findBySupplierCategoryIdAndSupplierStocksId(Long supplierCategoryId, Long supplierStocksId);


    List<SupplierStocks> findBySupplierProductNameContaining(String supplierProductName);


    @Query(
            value = "select * from supplier_stocks s where s.supplier_category_id=?1 and s.supplier_stocks_id=?2",
            nativeQuery = true
    )
    SupplierStocks findByCategoryAndSupplierId(Long supplierCategoryId, Long supplierStocksId);
}
