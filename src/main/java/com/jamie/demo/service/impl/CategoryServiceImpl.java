package com.jamie.demo.service.impl;

import java.util.List;

import com.jamie.demo.exception.ResourceNotFoundException;
import com.jamie.demo.model.Category;
import com.jamie.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.jamie.demo.service.CategoryService;

@Service  // this is the get post put delete
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    @Override
    public Category getCategoryByCategory_Id(int category_id) {
//		Optional<Category> category = categoryRepository.findById(Category_id);
//		if(category.isPresent()) {
//			return category.get();
//		}else {
//			throw new ResourceNotFoundException("Category", "category_id", category_id);
//		}
        return categoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "category_id", category_id));

    }

    @Override
    public Category updateCategory(Category category, int category_id) {

        // we need to check whether language with given id is exists in DB or not
        Category existingCategory = categoryRepository.findById(category_id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "category_id", category_id));

        existingCategory.setName(category.getName());
//        existingEmployee.setLastName(employee.getLastName());
//        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to DB
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(int category_id) {

        // check whether a category exist in a DB or not
        categoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "category_id", category_id));
        categoryRepository.deleteById(category_id);
    }

}