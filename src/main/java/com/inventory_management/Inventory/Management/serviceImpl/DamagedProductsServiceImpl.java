package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.service.DamagedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DamagedProductsServiceImpl implements DamagedProductsService {

    @Autowired
    private DamagedProductsRepository damagedProductsRepository;

    @Override
    public List<DamagedProducts> fetchAllDamagedProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 3);
        return damagedProductsRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<DamagedProducts> fetchDamagedProductById(Long damagedProductsId) throws NotFoundException {

        if (!damagedProductsRepository.existsById(damagedProductsId)) {
            throw new NotFoundException("Damaged Product with this Id does not exists");
        }
        return damagedProductsRepository.findById(damagedProductsId);
    }

    @Override
    public Object fetchAllDamagedProductsToReturn() {

        if (!damagedProductsRepository.existsByToReturnQuantityGreaterThan(Long.valueOf(0))){
            Message message=new Message();
            message.setMessage("All damaged products are returned");
            return message;
        }
        return damagedProductsRepository.findByToReturnQuantityGreaterThan(Long.valueOf(0));
    }
}
