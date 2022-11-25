package com.inventory_management.Inventory.Management.serviceImpl;



import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> fetchCategoryList() {
        return categoryRepository.findAll();
    }


    // fetch Category ById

    @Override
    public Category fetchCategoryById(Long categoryId) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (!category.isPresent()) {
            throw new NotFoundException("Category Id does not exist");
        }

        return category.get();
    }


    // fetch Category ByName

    @Override
    public Category fetchCategoryByName(String categoryName) throws NotFoundException {
        Optional<Category> category = categoryRepository.findByCategoryNameContaining(categoryName);

        if (!category.isPresent()) {
            throw new NotFoundException("Category name does not exist");
        }
        return category.get();
    }


    // Update Category

    @Override
    public Category updateCategory(Long categoryId, Category category) throws NotFoundException{

        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category with this id does not exist");
        }


        Category catDB = categoryRepository.findById(categoryId).get();

        if (Objects.nonNull(category.getCategoryName()) && !"".equalsIgnoreCase(category.getCategoryName())) {
            catDB.setCategoryName(category.getCategoryName());
        }

        return categoryRepository.save(catDB);
    }

    // Delete Category

    @Override
    public void deleteCategoryById(Long categoryId) throws NotFoundException{


        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category Id does not exist");
        }
        categoryRepository.deleteById(categoryId);

    }

}
