package com.jamie.demo.service;

import com.jamie.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategorys();
    Optional<Category> getCategoryById(int category_id);
    Category updateCategory(Category updatedCategory);
    void deleteCategory(int category_id);
}