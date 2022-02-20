package com.jamie.demo.service.impl;

import com.jamie.demo.model.Category;
import com.jamie.demo.repository.CategoryRepository;
import com.jamie.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(int category_id) {
        return categoryRepository.findById(category_id);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(int category_id) {
        categoryRepository.deleteById(category_id);
    }
}
