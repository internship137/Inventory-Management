package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.ReturnedProducts;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.ReturnedProductsRepository;
import com.inventory_management.Inventory.Management.service.ReturnedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReturnedProductsServiceImpl implements ReturnedProductsService {

    @Autowired
    private ReturnedProductsRepository returnedProductsRepository;

    @Autowired
    private DamagedProductsRepository damagedProductsRepository;

    @Override
    public Message returnDamagedProducts(Long damagedProductsId, ReturnedProducts returnedProducts) throws NotFoundException {
        if (!damagedProductsRepository.existsById(damagedProductsId)) {
            throw new NotFoundException("Damaged product does not exist");
        }

        Message message = new Message();

        if (Long.valueOf(returnedProducts.getReturnQuantity())==0){
            message.setMessage("Return quantity cannot be ZERO");
            return message;
        }

        DamagedProducts damagedProducts = damagedProductsRepository.findById(damagedProductsId).get();

        returnedProducts.setReturnProductName(damagedProducts.getDamagedProductName());
        returnedProducts.setProductCode(damagedProducts.getProductCode());
        returnedProducts.setProductManufacturer(damagedProducts.getProductManufacturer());
        returnedProducts.setSupplierName(damagedProducts.getSupplierName());
        returnedProducts.setSupplierCompany(damagedProducts.getSupplierCompany());

        returnedProducts.setReturnStatus("Return request initiated");



        Long returnQuantity = Long.valueOf(returnedProducts.getReturnQuantity());
        Long damagedQuantity = Long.valueOf(damagedProducts.getDamagedQuantity());

        if (returnQuantity > damagedQuantity) {
            message.setMessage("Return quantity should be less than Damaged Product Quantity");
            return message;
        }

        Long qty=damagedQuantity - returnQuantity;

        if (qty<0){
            message.setMessage("Not enough quantity in damaged item list");
            return message;
        }

        damagedProducts.setDamagedQuantity(String.valueOf(qty));

        damagedProductsRepository.save(damagedProducts);
        returnedProductsRepository.save(returnedProducts);

        message.setMessage("Return request successful");
        return message;
    }

    @Override
    public List<ReturnedProducts> fetchAllReturnRequests(int pageNo) {
        Pageable pageable= PageRequest.of(pageNo,3);
        return returnedProductsRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<ReturnedProducts> fetchReturnRequestById(Long returnProductsId) throws NotFoundException {
        if (!returnedProductsRepository.existsById(returnProductsId)){
            throw new NotFoundException("Return request with this Id does not exists");
        }
        
        return returnedProductsRepository.findById(returnProductsId);
        
    }
}
