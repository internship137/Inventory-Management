package com.inventory_management.Inventory.Management.serviceImpl;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.SupplierRepository;
import com.inventory_management.Inventory.Management.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Message addSupplier(Supplier supplier) {
//        Supplier supplier1=supplierRepository.findBySupplierName(supplier.getSupplierName()).orElse(null);

        if (supplierRepository.existsBySupplierCompanyIgnoreCase(supplier.getSupplierCompany())) {

            Message message = new Message();
            message.setMessage("Cannot add new supplier because supplier with this Company Name already exists");
            return message;
        }

        if (supplierRepository.existsBySupplierEmailIgnoreCase(supplier.getSupplierEmail())) {

            Message message = new Message();
            message.setMessage("Cannot add new supplier because supplier with this Email already exists");
            return message;
        }
        if (supplierRepository.existsBySupplierContactIgnoreCase(supplier.getSupplierContact())) {
            Message message = new Message();
            message.setMessage("Cannot add new supplier because supplier with this contact already exists");
            return message;
        }

        supplierRepository.save(supplier);
        Message message = new Message();
        message.setMessage("Supplier Added");
        return message;

    }

    @Override
    public List<Supplier> fetchSupplierList(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 3);
        return supplierRepository.findAll(pageable).get().toList();
    }


    @Override
    public Optional<Supplier> fetchSupplierById(Long supplierId) throws NotFoundException {

        Optional<Supplier> supplierID = supplierRepository.findById(supplierId);
        if (supplierID.isEmpty()) {
            throw new NotFoundException("Supplier with Id does not Exist");
        }
        return supplierRepository.findById(supplierId);
    }

    @Override
    public Message updateSupplier(Long supplierId, Supplier supplier) throws NotFoundException {

        if (!supplierRepository.existsById(supplierId)) {
            throw new NotFoundException("Supplier with this id does not exist");
        }

        Supplier supDB = supplierRepository.findById(supplierId).get();

        if (Objects.nonNull(supplier.getSupplierName()) && !"".equalsIgnoreCase(supplier.getSupplierName())) {
            supDB.setSupplierName(supplier.getSupplierName());
        }

        if (Objects.nonNull(supplier.getSupplierCompany()) && !"".equalsIgnoreCase(supplier.getSupplierCompany())) {
            supDB.setSupplierCompany(supplier.getSupplierCompany());

        }

        if (Objects.nonNull(supplier.getSupplierContact()) && !"".equalsIgnoreCase(supplier.getSupplierContact())) {
            supDB.setSupplierContact(supplier.getSupplierContact());
        }

        supplierRepository.save(supDB);
        Message message = new Message();
        message.setMessage("Updated Successfully");
        return message;
    }


    @Override
    public Message deleteSupplierById(Long supplierId) throws NotFoundException {

        if (!supplierRepository.existsById(supplierId)) {
            throw new NotFoundException("Product Id does not exist");
        }
        supplierRepository.deleteById(supplierId);
        Message message = new Message();
        message.setMessage("Deleted Successfully");
        return message;

    }

}