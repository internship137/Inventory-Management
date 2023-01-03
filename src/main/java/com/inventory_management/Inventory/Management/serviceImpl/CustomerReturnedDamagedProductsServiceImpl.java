package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.*;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.CustomerReturnedDamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.InvoiceRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.service.CustomerReturnedDamagedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerReturnedDamagedProductsServiceImpl implements CustomerReturnedDamagedProductsService {

    @Autowired
    private CustomerReturnedDamagedProductsRepository customerReturnedDamagedProductsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private DamagedProductsRepository damagedProductsRepository;


    @Override
    public Message customerReturnProductFromInvoice(CustomerReturnedDamagedProducts customerReturnedDamagedProducts, Long invoiceId) throws NotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Not found by invoice Id");
        }
        Invoice invoice = invoiceRepository.findById(invoiceId).get();

        String code = invoice.getProductCode();


        if (!productRepository.existsByProductCodeIgnoreCase(code)) {
            throw new NotFoundException("Not found by Product Code");
        }

        Product product = productRepository.findByProductCodeIgnoreCase(code);

        customerReturnedDamagedProducts.setInvoiceId(invoiceId);

        customerReturnedDamagedProducts.setCustomerName(invoice.getCustomerName());
        customerReturnedDamagedProducts.setCustomerEmail(invoice.getCustomerEmail());

        customerReturnedDamagedProducts.setProductCode(invoice.getProductCode());
        customerReturnedDamagedProducts.setProductName(invoice.getProductName());

        Message message = new Message();

        if (customerReturnedDamagedProductsRepository.existsByProductCodeIgnoreCaseAndInvoiceId(code, invoiceId)) {
            message.setMessage("Exists By Product Code And Invoice Id");
            return message;
        }

        Long a = Long.valueOf(invoice.getSellingQuantity());
        Long b = Long.valueOf(customerReturnedDamagedProducts.getCustomerReturnQuantity());

        if (b > a) {
            message.setMessage("Damaged quantity is greater than total item sold");
            return message;
        }

//        if (b == 0) {
//            message.setMessage("Damaged quantity cannot be ZERO ");
//            return message;
//        }
        customerReturnedDamagedProductsRepository.save(customerReturnedDamagedProducts);

        DamagedProducts damagedProducts = new DamagedProducts();

        if (!damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {
            damagedProducts.setProductName(invoice.getProductName());
            damagedProducts.setProductCode(invoice.getProductCode());
            damagedProducts.setSupplierName(product.getSupplierName());
            damagedProducts.setSupplierCompany(product.getSupplierCompany());


            damagedProducts.setCustomerReturnQuantity(Long.valueOf(customerReturnedDamagedProducts.getCustomerReturnQuantity()));
            damagedProducts.setToReturnQuantity(Long.valueOf(customerReturnedDamagedProducts.getCustomerReturnQuantity()));

            if (damagedProducts.getPurchaseOrderDamagedQuantity() == null) {
                damagedProducts.setPurchaseOrderDamagedQuantity(Long.valueOf(0));
            }

            damagedProductsRepository.save(damagedProducts);
            message.setMessage("New item added to damaged product list");
            return message;
        }

        if (damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {
            DamagedProducts damagedProducts1 = damagedProductsRepository.findByProductCodeIgnoreCase(code);

            Long currentCustomerReturnQty = damagedProducts1.getCustomerReturnQuantity();
            Long qty = Long.valueOf(customerReturnedDamagedProducts.getCustomerReturnQuantity());
            Long purchaseRequestReturnQty = damagedProducts1.getPurchaseOrderDamagedQuantity();

            damagedProducts1.setCustomerReturnQuantity(currentCustomerReturnQty + qty);
            damagedProducts1.setToReturnQuantity(currentCustomerReturnQty + qty + purchaseRequestReturnQty);
            damagedProductsRepository.save(damagedProducts1);
        }
        message.setMessage("Damaged quantity updated ");
        return message;
    }

    @Override
    public List<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceList(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 3);
        return customerReturnedDamagedProductsRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<CustomerReturnedDamagedProducts> fetchCustomerReturnProductFromInvoiceById(Long customerReturnProductsId) throws NotFoundException {

        if (!customerReturnedDamagedProductsRepository.existsById(customerReturnProductsId)) {
            throw new NotFoundException("Customer Returned products does not exists with this ID");
        }

        return customerReturnedDamagedProductsRepository.findById(customerReturnProductsId);
    }
}
