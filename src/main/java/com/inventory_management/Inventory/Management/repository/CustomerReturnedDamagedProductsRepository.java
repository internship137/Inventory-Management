package com.inventory_management.Inventory.Management.repository;

import com.inventory_management.Inventory.Management.entity.CustomerReturnedDamagedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReturnedDamagedProductsRepository extends JpaRepository<CustomerReturnedDamagedProducts, Long> {
    boolean existsByProductCodeIgnoreCaseAndInvoiceId(String productCode, Long invoiceId);

}
