package com.jamie.demo.service;


import com.jamie.demo.model.Category;

import java.util.List;



public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategory();
    Category getCategoryByCategory_Id(int category_id);

    Category updateCategory(Category category, int category_id);
    void deleteCategory(int category_id);
}