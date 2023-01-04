package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.*;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderDamagedProductRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderRepository;
import com.inventory_management.Inventory.Management.service.PurchaseOrderDamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderDamagedProductServiceImpl implements PurchaseOrderDamagedProductService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderDamagedProductRepository purchaseOrderDamagedProductRepository;

    @Autowired
    private DamagedProductsRepository damagedProductsRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Message addPurchaseOrderDamagedProduct(PurchaseOrderDamagedProduct purchaseOrderDamagedProduct, Long purchaseOrderId) throws NotFoundException {

        if (!purchaseOrderRepository.existsById(purchaseOrderId)) {
            throw new NotFoundException("Purchase request available for products existing in inventory ");
        }

        Message message = new Message();

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).get();



        purchaseOrderDamagedProduct.setPurchaseOrderId(purchaseOrderId);
        purchaseOrderDamagedProduct.setProductName(purchaseOrder.getProductName());
        purchaseOrderDamagedProduct.setProductCode(purchaseOrder.getProductCode());
        purchaseOrderDamagedProduct.setSupplierName(purchaseOrder.getSupplierName());

        String code = purchaseOrder.getProductCode();
        if (purchaseOrderDamagedProductRepository.existsByProductCodeIgnoreCaseAndPurchaseOrderId(code, purchaseOrderId)) {
            message.setMessage("Already exists with Product code and purchase order Id");
            return message;
        }
        Long a = Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity());
        Long b = purchaseOrder.getProductQuantity();

        if (a > b) {
            message.setMessage("Damaged quantity is greater than purchase order quantity");
            return message;
        }

        if (!purchaseOrder.getRequestStatus().equals("delivered")){
            message.setMessage("cannot add damaged products, because the order is not yet delivered or Rejected by supplier");
            return message;
        }

//        if (b == 0) {
//            message.setMessage("cannot be ZERO ");
//            return message;
//        }

        if (!productRepository.existsByProductNameIgnoreCase(purchaseOrder.getProductName())) {
            message.setMessage("Add the product to products lists before adding damaged product");
            return message;
        }



        purchaseOrderDamagedProductRepository.save(purchaseOrderDamagedProduct);

        DamagedProducts damagedProducts = new DamagedProducts();
        if (!damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {


            damagedProducts.setProductName(purchaseOrder.getProductName());
            damagedProducts.setProductCode(purchaseOrder.getProductCode());
            damagedProducts.setSupplierName(purchaseOrder.getSupplierName());
            damagedProducts.setSupplierCompany(purchaseOrder.getSupplierCompany());

            damagedProducts.setPurchaseOrderDamagedQuantity(Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity()));

            damagedProducts.setToReturnQuantity(Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity()));
            damagedProductsRepository.save(damagedProducts);
            message.setMessage("New item added to damaged product list");
            return message;
        }

        if (damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {
            DamagedProducts damagedProducts1 = damagedProductsRepository.findByProductCodeIgnoreCase(code);

            Long currentPurchaseOrderDamagedQty = damagedProducts1.getPurchaseOrderDamagedQuantity();

            Long qty = Long.valueOf(purchaseOrderDamagedProduct.getPurchaseOrderDamagedQuantity());

            Long currentCustomerReturnQty = damagedProducts1.getCustomerReturnQuantity();

            damagedProducts1.setPurchaseOrderDamagedQuantity(currentPurchaseOrderDamagedQty + a);
            damagedProducts1.setToReturnQuantity(currentPurchaseOrderDamagedQty + qty + currentCustomerReturnQty);
            damagedProductsRepository.save(damagedProducts1);
        }
        message.setMessage("Damaged quantity updated ");
        return message;
    }

    @Override
    public List<PurchaseOrderDamagedProduct> fetchPurchaseOrderDamagedProductsList(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 3);
        return purchaseOrderDamagedProductRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<PurchaseOrderDamagedProduct> fetchPurchaseOrderDamagedProductsId(Long purchaseOrderDamagedProductsId) throws NotFoundException {
        if (!purchaseOrderDamagedProductRepository.existsById(purchaseOrderDamagedProductsId)) {
            throw new NotFoundException("Purchase order damaged products not found with this Id");
        }

        return purchaseOrderDamagedProductRepository.findById(purchaseOrderDamagedProductsId);
    }

}
