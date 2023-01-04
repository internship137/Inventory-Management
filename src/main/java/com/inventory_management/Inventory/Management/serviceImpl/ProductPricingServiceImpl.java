package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.entity.ProductPricing;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.ProductPricingRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.service.ProductPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductPricingServiceImpl implements ProductPricingService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPricingRepository productPricingRepository;


    // Add Pricing

    @Override
    public Message savePricing(ProductPricing productPricing, Long productId) throws NotFoundException {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product with this Id does not exist");
        }

        Product product = productRepository.findById(productId).get();


        float byngPrice = Float.parseFloat(productPricing.getProductBuyingPrice());
        float gst = Float.parseFloat(productPricing.getGstSlab());
        Long profitMrgn = Long.valueOf(productPricing.getProfitMargin());
        Long mrp = Long.valueOf(productPricing.getMaximumRetailPrice());
        String prdctCode = product.getProductCode();


        Long lndingPrice = (long) (byngPrice + ((byngPrice / 100) * gst));
        Long cgst = (long) (gst / 2);
        Long sgst = (long) (gst / 2);
        Long sllngPrice = (lndingPrice + profitMrgn);


        productPricing.setCgst(cgst);
        productPricing.setSgst(sgst);
        productPricing.setProductCode(prdctCode);
        productPricing.setLandingPrice(lndingPrice);
        productPricing.setGstSlab(String.valueOf((long) gst));
        productPricing.setProductSellingPrice(sllngPrice);
        productPricing.setMaximumRetailPrice(String.valueOf(mrp));


        if (sllngPrice > mrp) {
            Message message = new Message();
            message.setMessage("Selling price should not be greater than MRP, check Profit Margin or buying Price");
            return message;
        }


        if (productPricingRepository.existsByProductCodeIgnoreCase(product.getProductCode())) {
            Message message = new Message();
            message.setMessage("Product Pricing Already Exists ");
            return message;
        }

        product.setProductPricing(productPricing);
        productPricingRepository.save(productPricing);

        Message message = new Message();
        message.setMessage("Product Pricing Added Successfully");
        return message;


    }

    // Get All pricing (Pagination and Sorting)

    @Override
    public List<ProductPricing> fetchPricingList(int pageNo, int recordCount) {
        PageRequest pageable = PageRequest.of(pageNo, recordCount,
                Sort.by("productPricingId"));
        return productPricingRepository.findAll(pageable).get().toList();
    }


    // Get Pricing By Pricing Id

    @Override
    public ProductPricing fetchProductPricingById(Long pricingId) throws NotFoundException {
        Optional<ProductPricing> productPricing = productPricingRepository.findById(pricingId);

        if (!productPricing.isPresent()) {
            throw new NotFoundException("Product Pricing does not exist");
        }
        return productPricing.get();
    }


    // Update pricing

    @Override
    public Message updatePricing(Long pricingId, ProductPricing productPricing) throws NotFoundException {

        if (!productPricingRepository.existsById(pricingId)) {
            throw new NotFoundException("Pricing with this Id does not exist");
        }

        ProductPricing pricingDB = productPricingRepository.findById(pricingId).get();


        float byngPrice = Float.parseFloat(productPricing.getProductBuyingPrice());
        float gst = Float.parseFloat(productPricing.getGstSlab());
        Long profitMrgn = Long.valueOf(productPricing.getProfitMargin());
        Long mrp = Long.valueOf(productPricing.getMaximumRetailPrice());


        Long lndingPrice = (long) (byngPrice + ((byngPrice / 100) * gst));
        Long cgst = (long) (gst / 2);
        Long sgst = (long) (gst / 2);
        Long sllngPrice = (lndingPrice + profitMrgn);

        pricingDB.setProductBuyingPrice(String.valueOf(byngPrice));
        pricingDB.setMaximumRetailPrice(String.valueOf(mrp));
        pricingDB.setLandingPrice(lndingPrice);
        pricingDB.setProfitMargin(String.valueOf(profitMrgn));
        pricingDB.setProductSellingPrice(sllngPrice);
        pricingDB.setGstSlab(String.valueOf(gst));
        pricingDB.setSgst(sgst);
        pricingDB.setCgst(cgst);


        if (Objects.nonNull(productPricing.getProfitMargin()) &&
                !"".equalsIgnoreCase(productPricing.getProfitMargin())) {
            pricingDB.setProfitMargin(productPricing.getProfitMargin());
        }

        if (Objects.nonNull(productPricing.getProductBuyingPrice()) &&
                !"".equalsIgnoreCase(productPricing.getProductBuyingPrice())) {
            pricingDB.setProductBuyingPrice(productPricing.getProductBuyingPrice());
        }

        if (Objects.nonNull(productPricing.getMaximumRetailPrice()) &&
                !"".equalsIgnoreCase(productPricing.getMaximumRetailPrice())) {
            pricingDB.setMaximumRetailPrice(productPricing.getMaximumRetailPrice());
        }

        if (Objects.nonNull(productPricing.getGstSlab()) &&
                !"".equalsIgnoreCase(productPricing.getGstSlab())) {
            pricingDB.setGstSlab(productPricing.getGstSlab());
        }


        productPricingRepository.save(pricingDB);

        Message message = new Message();
        message.setMessage("Product Pricing Updated Successfully");
        return message;
    }
}