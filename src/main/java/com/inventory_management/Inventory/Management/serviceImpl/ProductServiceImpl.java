package com.inventory_management.Inventory.Management.serviceImpl;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.dto.CategoryProductPricingDTO;
import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    // Add Products to Category


    @Override
    public Product saveProduct(Product product, Long categoryId) throws NotFoundException{
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(" Category with this Id does not exist");
        }

        float mrp = product.getMaximumRetailPrice();
        float discountPercentage = product.getPricingDiscountPercentage();

        float sellngPrice = product.getSellingPrice();

        sellngPrice = ((mrp) - (( discountPercentage/100)*mrp) );
        product.setSellingPrice((long) sellngPrice);


        Category category = categoryRepository.findById(categoryId).get();
        product.setCategory(category);
        return productRepository.save(product);
    }


    // Get all products with categories


    @Override
    public List<CategoryProductPricingDTO> fetchProductList() {
        return productRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get products from a specific category


    @Override
    public List<CategoryProductPricingDTO> fetchProductsByCategoryId(Long categoryId) throws NotFoundException {
        List<Product> productDto = productRepository.findProductByCategoryId(categoryId);

        if (productDto.isEmpty()) {
            throw new NotFoundException("Products with this Category id does not exist");
        }
        return productDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get a specific product from a specific category


    @Override
    public List<CategoryProductPricingDTO> fetchProductIdByCategoryId(Long categoryId, Long productId) throws NotFoundException {
        List<Product> productFromCategoryDto = productRepository.findProductIdByCategoryId(categoryId, productId);

        if (productFromCategoryDto.isEmpty()) {
            throw new NotFoundException("Product with this category or product id does not exist");
        }
        return productFromCategoryDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get product by product code (unique and String type)


    @Override
    public List<CategoryProductPricingDTO> fetchByProductCode(Long productCode) throws NotFoundException {
        List<Product> productCodeDto = productRepository.findByProductCode(productCode);

        if (productCodeDto.isEmpty()) {
            throw new NotFoundException("Product with this product code does not exist");
        }

        return productCodeDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get product by product name  (containing)


    @Override
    public List<CategoryProductPricingDTO> fetchByProductName(String productName) throws NotFoundException {
        List<Product> productNameDto = productRepository.findByProductNameContaining(productName);

        if (productNameDto.isEmpty()) {
            throw new NotFoundException("Product with this name does not exist");
        }

        return productNameDto
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get a product by productId


    @Override
    public List<CategoryProductPricingDTO> fetchByProductId(Long productId) throws NotFoundException {

        Optional<Product> productIdDto = productRepository.findById(productId);

        if (productIdDto.isEmpty()) {
            throw new NotFoundException("Product with this Id does not exist");
        }
        return productRepository.findById(productId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Update a specific product in a specific category


    @Override
    public Message updateProduct(Long categoryId, Long productId, Product product) throws NotFoundException{

        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product with this id does not exist");
        }
        Product proDB = productRepository.findProductIdUsingCategoryId(categoryId, productId);


        if (Objects.nonNull(product.getProductCode()) &&
                !"".equalsIgnoreCase(product.getProductCode())){
            proDB.setProductCode(product.getProductCode());
        }



        if (Objects.nonNull(product.getProductName()) &&
                !"".equalsIgnoreCase(product.getProductName())) {
            proDB.setProductName(product.getProductName());
        }

        if (Objects.nonNull(product.getProductBuyingPrice()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getProductBuyingPrice()))) {
            proDB.setProductBuyingPrice(product.getProductBuyingPrice());
        }

        if (Objects.nonNull(product.getMaximumRetailPrice()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getMaximumRetailPrice()))) {
            proDB.setMaximumRetailPrice(product.getMaximumRetailPrice());
        }


        if (Objects.nonNull(product.getProductManufacturer()) &&
                !"".equalsIgnoreCase(product.getProductManufacturer())) {
            proDB.setProductManufacturer(product.getProductManufacturer());
        }

        if (Objects.nonNull(product.getStockQuantity()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getStockQuantity()))) {
            proDB.setStockQuantity(product.getStockQuantity());
        }

//       selling price update

//      pricingDiscountPercentage

        if (Objects.nonNull(product.getPricingExpireDate()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getPricingExpireDate()))) {
            proDB.setPricingExpireDate(product.getPricingExpireDate());
        }


        productRepository.save(proDB);
        Message message=new Message();
        message.setMessage("Updated Successfully");
        return message;
    }


    //Delete a product


    @Override
    public Message deleteProduct(Long productId) throws NotFoundException {

        if (!categoryRepository.existsById(productId)) {
            throw new NotFoundException("Product Id does not exist");
        }
        productRepository.deleteById(productId);
        Message message=new Message();
        message.setMessage("Deleted Successfully");
        return message;

    }


    // DTO


    private CategoryProductPricingDTO convertEntityToDto(Product product) {
        CategoryProductPricingDTO categoryProductPricingDTO =
                new CategoryProductPricingDTO();

        categoryProductPricingDTO.setProductId(product.getProductId());
        categoryProductPricingDTO.setProductName(product.getProductName());
        categoryProductPricingDTO.setProductCode(product.getProductCode());
        categoryProductPricingDTO.setProductBuyingPrice(product.getProductBuyingPrice());
        categoryProductPricingDTO.setMaximumRetailPrice(product.getMaximumRetailPrice());
        categoryProductPricingDTO.setProductSellingPrice(product.getSellingPrice());
        categoryProductPricingDTO.setStockQuantity(product.getStockQuantity());
        categoryProductPricingDTO.setProductManufacturer(product.getProductManufacturer());
        categoryProductPricingDTO.setProductCreatedDateTime(product.getProductCreatedDateTime());
        categoryProductPricingDTO.setCategory(product.getCategory().getCategoryName());


        return categoryProductPricingDTO;
    }

}
