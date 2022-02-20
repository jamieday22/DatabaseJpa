package com.jamie.demo.controller;

import com.jamie.demo.model.Category;
import com.jamie.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.jamie.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategorys(){
        return categoryService.getAllCategorys();
    }

    @GetMapping("{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") int category_id){
        return categoryService.getCategoryById(category_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("category_id") int category_id,
                                                   @RequestBody Category category){
        return categoryService.getCategoryById(category_id)
                .map(savedCategory -> {

                    savedCategory.setName(category.getName());



                    Category updatedCategory = categoryService.updateCategory(savedCategory);
                    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{category_id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("category_id") int category_id){

        categoryService.deleteCategory(category_id);

        return new ResponseEntity<String>("Category deleted successfully!.", HttpStatus.OK);

    }

}
