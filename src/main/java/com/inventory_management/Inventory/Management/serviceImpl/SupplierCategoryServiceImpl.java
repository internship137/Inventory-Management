package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.SupplierCategory;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.SupplierStocksRepository;
import com.inventory_management.Inventory.Management.service.SupplierCategoryService;
import com.inventory_management.Inventory.Management.repository.SupplierCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierCategoryServiceImpl implements SupplierCategoryService {

    @Autowired
    private SupplierCategoryRepository supplierCategoryRepository;

    @Autowired
    private SupplierStocksRepository supplierStocksRepository;

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
        Optional<SupplierCategory> supplierCategory =
                supplierCategoryRepository.findBySupplierCategoryNameContaining(supplierCategoryName);

        if (supplierCategory.isEmpty()) {
            throw new NotFoundException("Category with such name does not exists");
        }
        return supplierCategory.get();
    }

    @Override
    public SupplierCategory fetchBySupplierCategoryId(Long supplierCategoryId) throws NotFoundException {
        Optional<SupplierCategory> supplierCategory =
                supplierCategoryRepository.findById(supplierCategoryId);

        if (supplierCategory.isEmpty()) {
            throw new NotFoundException("Category with this ID does not exists");
        }
        return supplierCategory.get();
    }



    @Override
    public Message updateSupplierCategory(Long supplierCategoryId, SupplierCategory supplierCategory) throws NotFoundException {
        if (!supplierCategoryRepository.existsById(supplierCategoryId)) {
            throw new NotFoundException("Category with this id does not exist");
        }
        SupplierCategory supplierCat = supplierCategoryRepository.findById(supplierCategoryId).get();

        if (Objects.nonNull(supplierCategory.getSupplierCategoryName()) &&
                !"".equalsIgnoreCase(supplierCategory.getSupplierCategoryName())) {
            supplierCat.setSupplierCategoryName(supplierCategory.getSupplierCategoryName());
        }
        supplierCategoryRepository.save(supplierCat);
        Message message=new Message();
        message.setMessage("successfully updated");
        return message;

    }

}
