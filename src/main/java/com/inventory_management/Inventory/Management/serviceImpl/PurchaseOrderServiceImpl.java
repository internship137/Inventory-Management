package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.*;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderVerificationTokenRepository;
import com.inventory_management.Inventory.Management.repository.SupplierRepository;
import com.inventory_management.Inventory.Management.repository.PurchaseOrderRepository;
import com.inventory_management.Inventory.Management.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseOrderVerificationTokenRepository purchaseOrderVerificationTokenRepository;

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) throws NotFoundException {

//        if (!supplierRepository.existsById(supplierId)) {
//            throw new NotFoundException("Not found");
//        }
        if (!productRepository.existsByProductCodeIgnoreCase(purchaseOrder.getProductCode())) {
            throw new NotFoundException("Product code does not exist");
        }

        Product product = productRepository.findByProductCodeIgnoreCase(purchaseOrder.getProductCode());

        Supplier supplier = supplierRepository.findBySupplierCompanyIgnoreCase(product.getSupplierCompany());

//        if (!productRepository.existsByProductNameIgnoreCase(purchaseOrder.getProductName())) {
//
//
//            purchaseOrder.setProductCode("new product");
//            purchaseOrder.setSupplierId(supplierId);
//            purchaseOrder.setSupplierName(supplier.getSupplierName());
//            purchaseOrder.setSupplierEmail(supplier.getSupplierEmail());
//            purchaseOrder.setSupplierCompany(supplier.getSupplierCompany());
//            purchaseOrder.setRequestStatus("Requested");
//            purchaseOrder.setSupplierStatusDate("waiting");
//            return purchaseOrderRepository.save(purchaseOrder);
//
//        }


        purchaseOrder.setProductName(product.getProductName());

        purchaseOrder.setSupplierId(supplier.getSupplierId());
        purchaseOrder.setSupplierName(supplier.getSupplierName());
        purchaseOrder.setSupplierEmail(supplier.getSupplierEmail());
        purchaseOrder.setSupplierCompany(supplier.getSupplierCompany());
        purchaseOrder.setRequestStatus("Requested");
        purchaseOrder.setSupplierStatusDate("waiting");
        return purchaseOrderRepository.save(purchaseOrder);

    }

    @Override
    public void saveTokens(PurchaseOrder purchaseOrder, String token, String reject) {

        PurchaseOrderVerificationToken purchaseOrderVerificationToken =
                new PurchaseOrderVerificationToken(purchaseOrder, token, reject);

        purchaseOrderVerificationTokenRepository.save(purchaseOrderVerificationToken);
    }

    @Override
    public Message confirmOrderStatus(String token) {
        Message message = new Message();
        PurchaseOrderVerificationToken purchaseOrderVerificationToken =
                purchaseOrderVerificationTokenRepository.findByToken(token);
        if (purchaseOrderVerificationToken == null) {
            message.setMessage("Invalid Token");
            return message;
        }

        PurchaseOrder purchaseOrder = purchaseOrderVerificationToken.getPurchaseOrder();
        Calendar cal = Calendar.getInstance();

        if ((purchaseOrderVerificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0)) {
            purchaseOrderVerificationTokenRepository.delete(purchaseOrderVerificationToken);
            message.setMessage("Token Expired");
            return message;
        }

        purchaseOrder.setRequestStatus("Approved by supplier");
        purchaseOrder.setSupplierStatusDate(String.valueOf(new Date()));
        purchaseOrderRepository.save(purchaseOrder);
        purchaseOrderVerificationTokenRepository.delete(purchaseOrderVerificationToken);
        message.setMessage("Thank you for approving");
        return message;
    }

    @Override
    public Message rejectOrder(String reject) {
        Message message = new Message();

        PurchaseOrderVerificationToken purchaseOrderVerificationToken =
                purchaseOrderVerificationTokenRepository.findByReject(reject);
        if (purchaseOrderVerificationToken == null) {
            message.setMessage("Invalid Token");
            return message;
        }

        PurchaseOrder purchaseOrder = purchaseOrderVerificationToken.getPurchaseOrder();
        Calendar cal = Calendar.getInstance();

        if ((purchaseOrderVerificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0)) {
            purchaseOrderVerificationTokenRepository.delete(purchaseOrderVerificationToken);
            message.setMessage("Token Expired");
            return message;
        }

        purchaseOrder.setRequestStatus("Rejected By Supplier");
        purchaseOrder.setSupplierStatusDate(String.valueOf(new Date()));
        purchaseOrderRepository.save(purchaseOrder);
        message.setMessage("Request Rejected");
        return message;
    }

    @Override
    public List<PurchaseOrder> fetchAllRequest(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 3);
        return purchaseOrderRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<PurchaseOrder> fetchRequestsById(Long purchaseRequestId) throws NotFoundException {
        if (!purchaseOrderRepository.existsById(purchaseRequestId)) {
            throw new NotFoundException("No purchase order exists with this Id");
        }
        return purchaseOrderRepository.findById(purchaseRequestId);
    }

    @Override
    public Message changeStatus(Long purchaseRequestId) throws NotFoundException {
        if (!purchaseOrderRepository.existsById(purchaseRequestId)) {
            throw new NotFoundException("No purchase order exists with this Id");
        }

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseRequestId).get();

        if (purchaseOrder.getRequestStatus().equals("Approved by supplier")) {

            purchaseOrder.setRequestStatus("delivered");
            purchaseOrderRepository.save(purchaseOrder);
            Message message = new Message();
            message.setMessage("Status changed");
            return message;
        }

        if (purchaseOrder.getRequestStatus().equals("Requested")) {
            Message message = new Message();
            message.setMessage("not yet approved or rejected by supplier");
            return message;
        }
        Message message = new Message();
        message.setMessage("cannot change status because order was rejected by supplier");
        return message;

    }
}



