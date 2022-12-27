package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.*;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderDamagedProductRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderRepository;
import com.inventory_management.Inventory.Management.service.PurchaseOrderDamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderDamagedProductServiceImpl implements PurchaseOrderDamagedProductService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderDamagedProductRepository purchaseOrderDamagedProductRepository;

    @Autowired
    private DamagedProductsRepository damagedProductsRepository;

    @Override
    public Message addPurchaseOrderDamagedProduct(PurchaseOrderDamagedProduct purchaseOrderDamagedProduct, Long purchaseOrderId) throws NotFoundException {

        if (!purchaseOrderRepository.existsById(purchaseOrderId)) {
            throw new NotFoundException("Not found");
        }

        Message message = new Message();

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).get();

        purchaseOrderDamagedProduct.setPurchaseOrderId(purchaseOrderId);
        purchaseOrderDamagedProduct.setProductName(purchaseOrder.getProductName());
        purchaseOrderDamagedProduct.setProductCode(purchaseOrder.getProductCode());
        purchaseOrderDamagedProduct.setSupplierName(purchaseOrder.getSupplierName());

        String code = purchaseOrder.getProductCode();
        if (purchaseOrderDamagedProductRepository.existsByProductCodeIgnoreCaseAndPurchaseOrderId(code, purchaseOrderId)) {
            message.setMessage("exists");
            return message;
        }
        Long a = Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity());
        Long b = purchaseOrder.getProductQuantity();

        if (a > b) {
            message.setMessage("cannot save purchase order qty > damaged quantity");
            return message;
        }

        if (b == 0) {
            message.setMessage("cannot be ZERO ");
            return message;
        }

        purchaseOrderDamagedProductRepository.save(purchaseOrderDamagedProduct);

        DamagedProducts damagedProducts = new DamagedProducts();
        if (!damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {


            damagedProducts.setProductName(purchaseOrder.getProductName());
            damagedProducts.setProductCode(purchaseOrder.getProductCode());
            damagedProducts.setProductCategory(purchaseOrder.getProductCategory());
            damagedProducts.setSupplierName(purchaseOrder.getSupplierName());
            damagedProducts.setSupplierCompany(purchaseOrder.getSupplierCompany());

            damagedProducts.setPurchaseOrderDamagedQuantity(damagedProducts.getPurchaseOrderDamagedQuantity());

            damagedProductsRepository.save(damagedProducts);
        }

        if (damagedProductsRepository.existsByProductCodeIgnoreCase(code)){
            DamagedProducts damagedProducts1 = damagedProductsRepository.findByProductCodeIgnoreCase(code);

            Long currentPurchaseOrderDamagedQty=damagedProducts1.getPurchaseOrderDamagedQuantity();

            Long qty= Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity());

            Long currentCustomerReturnQty=damagedProducts1.getCustomerReturnQuantity();

            damagedProducts1.setPurchaseOrderDamagedQuantity(currentPurchaseOrderDamagedQty+a);
            damagedProducts1.setToReturnQuantity(currentPurchaseOrderDamagedQty+qty+currentCustomerReturnQty);
            damagedProductsRepository.save(damagedProducts1);
        }
        message.setMessage("saved ");
        return message;
    }

}
