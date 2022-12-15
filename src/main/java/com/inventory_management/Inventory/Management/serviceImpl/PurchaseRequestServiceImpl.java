package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import com.inventory_management.Inventory.Management.entity.PurchaseRequestVerificationToken;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.repository.PurchaseRequestVerificationTokenRepository;
import com.inventory_management.Inventory.Management.repository.SupplierRepository;
import com.inventory_management.Inventory.Management.service.PurchaseRequestRepository;
import com.inventory_management.Inventory.Management.service.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;
    @Autowired
    private PurchaseRequestVerificationTokenRepository purchaseRequestVerificationTokenRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public PurchaseRequest saveOrder(PurchaseRequest purchaseRequest, Long supplierId) {
        Supplier supplier=supplierRepository.findById(supplierId).get();
        purchaseRequest.setSupplierId(supplierId);
        purchaseRequest.setSupplierName(supplier.getSupplierName());
        purchaseRequest.setSupplierEmail(supplier.getSupplierEmail());
        purchaseRequest.setRequestStatus("Requested");
        return purchaseRequestRepository.save(purchaseRequest);
    }

    @Override
    public void saveTokens(PurchaseRequest purchaseRequest, String token, String reject) {

        PurchaseRequestVerificationToken purchaseRequestVerificationToken =
                new PurchaseRequestVerificationToken(purchaseRequest, token, reject);

        purchaseRequestVerificationTokenRepository.save(purchaseRequestVerificationToken);
    }

    @Override
    public Message confirmOrderStatus(String token) {
        Message message=new Message();
        PurchaseRequestVerificationToken purchaseRequestVerificationToken =
                purchaseRequestVerificationTokenRepository.findByToken(token);
        if (purchaseRequestVerificationToken == null) {
            message.setMessage("Invalid Token");
            return message;
        }

        PurchaseRequest purchaseRequest = purchaseRequestVerificationToken.getPurchaseRequest();
        Calendar cal = Calendar.getInstance();

        if ((purchaseRequestVerificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0)) {
            purchaseRequestVerificationTokenRepository.delete(purchaseRequestVerificationToken);
            message.setMessage("Token Expired");
            return message;
        }

        purchaseRequest.setRequestStatus("Approved by supplier");
        purchaseRequestRepository.save(purchaseRequest);
        message.setMessage("Request Approved");
        return message;
    }

    @Override
    public Message rejectOrder(String reject) {
        Message message=new Message();

        PurchaseRequestVerificationToken purchaseRequestVerificationToken =
                purchaseRequestVerificationTokenRepository.findByReject(reject);
        if (purchaseRequestVerificationToken == null) {
            message.setMessage("Invalid Token");
            return message;
        }

        PurchaseRequest purchaseRequest = purchaseRequestVerificationToken.getPurchaseRequest();
        Calendar cal = Calendar.getInstance();

        if ((purchaseRequestVerificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0)) {
            purchaseRequestVerificationTokenRepository.delete(purchaseRequestVerificationToken);
            message.setMessage("Token Expired");
            return message;
        }

        purchaseRequest.setRequestStatus("Rejected By Supplier");
        purchaseRequestRepository.save(purchaseRequest);
        message.setMessage("Request Rejected");
        return message;
    }

}