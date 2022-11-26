package com.inventory_management.Inventory.Management.serviceImpl;


import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.SupplierRepository;
import com.inventory_management.Inventory.Management.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> fetchSupplierList() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> fetchSupplierById(Long supplierId) throws NotFoundException {

        Optional<Supplier> supplierID = supplierRepository.findById(supplierId);
        if(supplierID.isEmpty()){
            throw new NotFoundException("Supplier with Id does not Exist");
        }
        return supplierRepository.findById(supplierId);
    }

    @Override
    public List<Supplier> fetchSupplierByName(String supplierName) throws NotFoundException {

        List<Supplier> supplierNAME = supplierRepository.findBySupplierNameContaining(supplierName);
        if(supplierNAME.isEmpty()){
            throw new NotFoundException("Supplier with this name does not Exist");
        }
        return supplierRepository.findBySupplierNameContaining(supplierName);
    }

    @Override
    public List<Supplier> fetchSupplierByCompanyName(String supplierCompany) throws NotFoundException {

        List<Supplier> supplierCOMPANY = supplierRepository.findBySupplierCompanyContaining(supplierCompany);
        if (supplierCOMPANY.isEmpty()){
            throw new NotFoundException("Supplier company with this name does not exist");
        }
        return supplierRepository.findBySupplierCompanyContaining(supplierCompany);
    }

    @Override
    public Supplier updateSupplier(Long supplierId, Supplier supplier) throws NotFoundException{

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

        return supplierRepository.save(supDB);
    }

    @Override
    public void deleteSupplierById(Long supplierId) throws NotFoundException {

        if (!supplierRepository.existsById(supplierId)) {
            throw new NotFoundException("Product Id does not exist");
        }
        supplierRepository.deleteById(supplierId);

    }

}
