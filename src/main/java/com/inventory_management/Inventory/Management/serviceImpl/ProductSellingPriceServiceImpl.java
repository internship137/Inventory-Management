package com.inventory_management.Inventory.Management.serviceImpl;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.entity.ProductSellingPrice;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.ProductSellingPriceRepository;
import com.inventory_management.Inventory.Management.service.ProductSellingPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductSellingPriceServiceImpl implements ProductSellingPriceService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSellingPriceRepository productSellingPriceRepository;


    // Add Pricing to products


    @Override
    public ProductSellingPrice saveSellingPrice(Long productId, ProductSellingPrice productSellingPrice)  throws NotFoundException{

        if (!productRepository.existsById(productId)){
            throw new NotFoundException("Product with this ID does not exist");
        }
        Product product = productRepository.findById(productId).get();

        float mrp = product.getMaximumRetailPrice();
        float dicountPercentage = productSellingPrice.getPricingDiscountPercentage();

        float sllngPrice = productSellingPrice.getSellingPrice();

        sllngPrice = ((mrp) - (( dicountPercentage/100)*mrp) );
        productSellingPrice.setSellingPrice((long) sllngPrice);

        return productSellingPriceRepository.save(productSellingPrice);


    }

    // Get All Pricing

    @Override
    public List<ProductSellingPrice> fetchAllPricing() {
        return productSellingPriceRepository.findAll();
    }


    // Get Pricing By ID


    @Override
    public ProductSellingPrice fetchPricingById(Long sellingPriceId) throws NotFoundException {

        Optional<ProductSellingPrice> sellingPriceID = productSellingPriceRepository.findById(sellingPriceId);
        if (sellingPriceID.isEmpty()) {
            throw new NotFoundException("Pricing Id does not exist");
        }
        return productSellingPriceRepository.findById(sellingPriceId).get();
    }


    // Get Pricing by selling price


    @Override
    public List<ProductSellingPrice> fetchPricingBySellingPrice(Long sellingPrice) throws NotFoundException {


        List<ProductSellingPrice> sellingPRICE = productSellingPriceRepository.findBySellingPrice(sellingPrice);
        if (sellingPRICE.isEmpty()) {
            throw new NotFoundException("Pricing with entered selling price does not exist");
        }
        return productSellingPriceRepository.findBySellingPrice(sellingPrice);
    }


    // update pricing


    @Override
    public Message updateProductSellingPrice(Long sellingPriceId,
                                             ProductSellingPrice productSellingPrice) throws NotFoundException{

        if (!productSellingPriceRepository.existsById(sellingPriceId)) {
            throw new NotFoundException("Selling Price with this id does not exist");
        }
        ProductSellingPrice pricingDB = productSellingPriceRepository.findById(sellingPriceId).get();


        if (Objects.nonNull(productSellingPrice.getSellingPrice()) &&
                !"".equalsIgnoreCase(String.valueOf(productSellingPrice.getSellingPrice()))) {
            pricingDB.setSellingPrice(productSellingPrice.getSellingPrice());
        }

        if (Objects.nonNull(productSellingPrice.getPricingDiscountPercentage()) &&
                !"".equalsIgnoreCase(String.valueOf(productSellingPrice.getPricingDiscountPercentage()))) {
            pricingDB.setPricingDiscountPercentage(productSellingPrice.getPricingDiscountPercentage());
        }


        if (Objects.nonNull(productSellingPrice.getPricingExpireDate()) &&
                !"".equalsIgnoreCase(String.valueOf(productSellingPrice.getPricingExpireDate()))) {
            pricingDB.setPricingExpireDate(productSellingPrice.getPricingExpireDate());
        }

        productSellingPriceRepository.save(pricingDB);

        Message message=new Message();
        message.setMessage("Updated Successfully");
        return message;

    }

}

