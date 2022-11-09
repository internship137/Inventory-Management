package com.inventory_management.Inventory.Management.service;

import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.SupplierCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierCategoryServiceImpl implements SupplierCategoryService{

    @Autowired
    private SupplierCategoryRepository supplierCategoryRepository;
    @Override
    public SupplierCategory saveCategory(SupplierCategory supplierCategory) {
        return supplierCategoryRepository.save(supplierCategory);
    }

    @Override
    public List<SupplierCategory> fetchAllCategoriesList() {
        return supplierCategoryRepository.findAll();
    }

    @Override
    public SupplierCategory fetchBySupplierCategoryName(String supplierCategoryName) throws NotFoundException {
        Optional<SupplierCategory> supplierCategory=
                supplierCategoryRepository.findBySupplierCategoryNameIgnoreCase(supplierCategoryName);

        if (!supplierCategory.isPresent()){
            throw new NotFoundException("Category with such name does not exists");
        }
        return supplierCategory.get();
    }

    @Override
    public SupplierCategory fetchBySupplierCategoryId(Long supplierCategoryId) throws NotFoundException {
        Optional<SupplierCategory> supplierCategory=
                supplierCategoryRepository.findById(supplierCategoryId);

        if (!supplierCategory.isPresent()){
            throw new NotFoundException("Category with this ID does not exists");
        }
        return supplierCategory.get();
    }

    @Override
    public void deleteSupplierCategoryById(Long supplierCategoryId) throws NotFoundException {
        if (!supplierCategoryRepository.existsById(supplierCategoryId)){
            throw new NotFoundException("Invalid Id provided");
        }

        supplierCategoryRepository.deleteById(supplierCategoryId);
    }

    @Override
    public SupplierCategory updateSupplierCategory(Long supplierCategoryId, SupplierCategory supplierCategory) {
        SupplierCategory supplierCat=supplierCategoryRepository.findById(supplierCategoryId).get();

        if (Objects.nonNull(supplierCategory.getSupplierCategoryName()) &&
                !"".equalsIgnoreCase(supplierCategory.getSupplierCategoryName())){
            supplierCat.setSupplierCategoryName(supplierCategory.getSupplierCategoryName());
        }
        return supplierCategoryRepository.save(supplierCat);
    }

}
