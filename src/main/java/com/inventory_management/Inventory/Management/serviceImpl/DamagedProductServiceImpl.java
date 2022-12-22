package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.DamagedProducts;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.DamagedProductsRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.service.DamagedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DamagedProductServiceImpl implements DamagedProductService {
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private DamagedProductsRepository damagedProductsRepository;


    @Override
    public Message addDamagedProduct(DamagedProducts damagedProducts) throws NotFoundException {


        String code = damagedProducts.getProductCode();

        if (!productRepository.existsByProductCodeIgnoreCase(code)) {
            throw new NotFoundException("Product with this Id doesn't exist");
        }

        Product product = productRepository.findByProductCodeIgnoreCase(code);


        Long totalQty = Long.valueOf(product.getStockQuantity());
        Long damagedQty = Long.valueOf(damagedProducts.getDamagedQuantity());
        if (damagedQty > totalQty) {
            Message message = new Message();
            message.setMessage("Please check the damaged item quantity");
            return message;
        }

        if (Long.valueOf(damagedQty)<=0){
            Message message = new Message();
            message.setMessage("Quantity should not be less than Zero");
            return message;
        }

//        if (damagedProductsRepository.existsByProductCodeIgnoreCase(code)) {
//
//            DamagedProducts damagedProducts1 = damagedProductsRepository.findByProductCodeIgnoreCase(code);
//            Long totalDamagedQty = damagedProducts1.getDamagedQuantity();
//            Long qty = damagedProducts.getDamagedQuantity();
//
//            damagedProducts1.setDamagedQuantity(totalDamagedQty + qty);
//
//
//            if (damagedProducts1.getDamagedQuantity() > damagedProducts1.getTotalQuantity()) {
//
//                Message message = new Message();
//                message.setMessage("Damaged quantity cannot be updated..!! Damaged quantity is greater than total product quantity");
//                return message;
//
//
//            }
//            damagedProductsRepository.save(damagedProducts1);
//            Message message = new Message();
//            message.setMessage("A product with this id exist. Damaged quantity is updated");
//            return message;
//
//        }


        damagedProducts.setDamagedProductName(product.getProductName());
        damagedProducts.setProductCategory(product.getCategory().getCategoryName());
        damagedProducts.setProductManufacturer(product.getProductManufacturer());
        damagedProducts.setTotalQuantity(Long.valueOf(product.getStockQuantity()));
        damagedProducts.setSupplierName(product.getSupplierName());
        damagedProducts.setSupplierCompany(product.getSupplierCompany());

        damagedProductsRepository.save(damagedProducts);

        Message message = new Message();
        message.setMessage("Item added in damaged product list");
        return message;

    }

    @Override
    public List<DamagedProducts> fetchAllDamagedProducts(int pageNo) {
        Pageable pageable= PageRequest.of(pageNo,3);
        return damagedProductsRepository.findAll(pageable).get().toList();
    }

    @Override
    public Optional<DamagedProducts> fetchDamagedProductsById(Long damagedProductsId) throws NotFoundException {
        if (!damagedProductsRepository.existsById(damagedProductsId)) {
            throw new NotFoundException("Damaged product with this id does not exist");
        }
        return damagedProductsRepository.findById(damagedProductsId);
    }

    @Override
    public Message updateDamagedProduct(Long damagedProductsId,@Valid @RequestBody DamagedProducts damagedProducts) throws NotFoundException {

        if (!damagedProductsRepository.existsById(damagedProductsId)) {
            throw new NotFoundException("Damaged product with this id does not exist");
        }

        DamagedProducts damagedProducts1=damagedProductsRepository.findById(damagedProductsId).get();

        Product product = productRepository.findById(damagedProductsId).get();


        Long totalQty = Long.valueOf(product.getStockQuantity());
        Long damagedQty = Long.valueOf(damagedProducts.getDamagedQuantity());
        if (damagedQty > totalQty) {
            Message message = new Message();
            message.setMessage("Please check the damaged item quantity");
            return message;
        }

        if (damagedQty==0){
            Message message = new Message();
            message.setMessage("cannot be zero");
            return message;
        }

        if (Objects.nonNull(damagedProducts.getDamagedQuantity()) && !"".equalsIgnoreCase(damagedProducts.getDamagedQuantity())){

            damagedProducts1.setDamagedQuantity(damagedProducts.getDamagedQuantity());
            damagedProductsRepository.save(damagedProducts1);
        }
        Message message = new Message();
        message.setMessage("Updated the damaged quantity");
        return message;
    }
}
