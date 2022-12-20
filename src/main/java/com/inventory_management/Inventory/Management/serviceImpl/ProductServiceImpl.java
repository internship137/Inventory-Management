package com.inventory_management.Inventory.Management.serviceImpl;


import com.inventory_management.Inventory.Management.dto.ProductDTO;
import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.entity.Supplier;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.repository.SupplierRepository;
import com.inventory_management.Inventory.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    // Add Products to Category


    @Override
    public Message saveProduct(Product product, Long categoryId, Long supplierId) throws NotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(" Category with this Id does not exist");
        }
        if (!supplierRepository.existsById(supplierId)) {
            throw new NotFoundException(" Supplier with this Id does not exist");
        }


        if (productRepository.existsByProductNameIgnoreCase(product.getProductName())) {

            Message message = new Message();
            message.setMessage("Product Name already exists ");
            return message;
        }

        if (productRepository.existsByProductCodeIgnoreCase(product.getProductCode())) {

            Message message = new Message();
            message.setMessage("Product Code already exists ");
            return message;
        }

        // supplier

        Supplier supplier = supplierRepository.findById(supplierId).get();

//        String supplierName = supplier.getSupplierName();
//        String supplierCompany = supplier.getSupplierCompany();

//        supplier.setSupplierName(supplierName);
//        supplier.setSupplierCompany(supplierCompany);

        product.setSupplierName(supplier.getSupplierName());
        product.setSupplierCompany(supplier.getSupplierCompany());

        supplierRepository.save(supplier);
//        productRepository.save(product);

        // selling price logic

        float mrp = Float.parseFloat(product.getMaximumRetailPrice());
        float discountPercentage = Float.parseFloat(product.getPricingDiscountPercentage());

        float sellngPrice = product.getSellingPrice();

        sellngPrice = ((mrp) - ((discountPercentage / 100) * mrp));
        product.setSellingPrice((long) sellngPrice);


        Category category = categoryRepository.findById(categoryId).get();
        product.setCategory(category);
        productRepository.save(product);

        Message message = new Message();
        message.setMessage("Product Added");
        return message;

    }

    // Get all products with categories (Pagination and Sorting)


    @Override
    public List<ProductDTO> fetchProductList(int pageNo, int recordCount) {
        Pageable pageable = PageRequest.of(pageNo, recordCount,
                Sort.by("productId"));
        return productRepository.findAll(pageable)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get products from a specific category


    @Override
    public List<ProductDTO> fetchProductsByCategoryId(Long categoryId, int pageNo, int recordCount)
            throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNo, recordCount);
        List<Product> productDto = productRepository.findProductByCategoryId(categoryId, pageable);


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
    public List<ProductDTO> fetchProductIdByCategoryId(Long categoryId, Long productId) throws NotFoundException {
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
    public List<ProductDTO> fetchByProductCode(String productCode) throws NotFoundException {
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
    public List<ProductDTO> fetchByProductName(String productName) throws NotFoundException {
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
    public List<ProductDTO> fetchByProductId(Long productId) throws NotFoundException {

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
    public Message updateProduct(Long categoryId, Long productId, Product product) throws NotFoundException {

        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product with this id does not exist");
        }


        Product proDB = productRepository.findProductIdUsingCategoryId(categoryId, productId);


        if (Objects.nonNull(product.getProductCode()) &&
                !"".equalsIgnoreCase(product.getProductCode())) {
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

//        if (Objects.nonNull(product.getSellingPrice()) &&
//                !"".equalsIgnoreCase(String.valueOf(product.getSellingPrice()))) {
//            proDB.setSellingPrice(product.getSellingPrice());
//        }

//      pricingDiscountPercentage

        if (Objects.nonNull(product.getPricingDiscountPercentage()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getPricingDiscountPercentage()))) {
            proDB.setPricingDiscountPercentage(product.getPricingDiscountPercentage());
        }


        if (Objects.nonNull(product.getPricingExpireDate()) &&
                !"".equalsIgnoreCase(String.valueOf(product.getPricingExpireDate()))) {
            proDB.setPricingExpireDate(product.getPricingExpireDate());
        }


        productRepository.save(proDB);
        Message message = new Message();
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
        Message message = new Message();
        message.setMessage("Deleted Successfully");
        return message;

    }


    // DTO


    private ProductDTO convertEntityToDto(Product product) {
        ProductDTO categoryProductPricingDTO =
                new ProductDTO();

        categoryProductPricingDTO.setProductId(product.getProductId());
        categoryProductPricingDTO.setProductName(product.getProductName());
        categoryProductPricingDTO.setProductCode(product.getProductCode());
        categoryProductPricingDTO.setProductBuyingPrice(Long.valueOf(product.getProductBuyingPrice()));
        categoryProductPricingDTO.setMaximumRetailPrice(Long.valueOf(product.getMaximumRetailPrice()));
        categoryProductPricingDTO.setProductSellingPrice(product.getSellingPrice());
        categoryProductPricingDTO.setStockQuantity(Long.valueOf(product.getStockQuantity()));
        categoryProductPricingDTO.setProductManufacturer(product.getProductManufacturer());
        categoryProductPricingDTO.setProductCreatedDateTime(product.getProductCreatedDateTime());
        categoryProductPricingDTO.setCategory(product.getCategory().getCategoryName());
        categoryProductPricingDTO.setSupplierName(product.getSupplierName());
        categoryProductPricingDTO.setSupplierCompany(product.getSupplierCompany());


        return categoryProductPricingDTO;
    }

}
