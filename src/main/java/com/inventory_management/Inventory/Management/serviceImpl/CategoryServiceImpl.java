package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.Category;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.CategoryRepository;
import com.inventory_management.Inventory.Management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Add Category

    @Override
    public Message saveCategory(Category category) {

        if (categoryRepository.existsByCategoryNameIgnoreCase(category.getCategoryName())) {

            Message message = new Message();
            message.setMessage("Category Name already exists ");
            return message;
        }
        categoryRepository.save(category);
        Message message = new Message();
        message.setMessage("Category Added Successfully");
        return message;
    }



    // Get all Category (Pagination and Sorting)

    @Override
    public List<Category> fetchCategoryList(int pageNo, int recordCount) {
        Pageable pageable = PageRequest.of(pageNo, recordCount,
                Sort.by("categoryId"));
        return categoryRepository.findAll(pageable).get().toList();
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
    public Message updateCategory(Long categoryId, Category category) throws NotFoundException {

        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category with this id does not exist");
        }


        Category catDB = categoryRepository.findById(categoryId).get();

        if (Objects.nonNull(category.getCategoryName()) && !"".equalsIgnoreCase(category.getCategoryName())) {
            catDB.setCategoryName(category.getCategoryName());
        }

        categoryRepository.save(catDB);
        Message message = new Message();
        message.setMessage("Category Updated Successfully");
        return message;
    }

}
