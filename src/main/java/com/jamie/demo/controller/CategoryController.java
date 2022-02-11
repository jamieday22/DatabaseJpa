package com.jamie.demo.controller;

import java.util.List;

import com.jamie.demo.model.Category;
import com.jamie.demo.model.Language;
import com.jamie.demo.service.CategoryService;
import com.jamie.demo.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    // build create Category REST API
    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    // build get all Category REST API
    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    // build get category by id REST API
    // http://localhost:8080/api/langauge/1
    @GetMapping("{category_id}")
    public ResponseEntity<Category> getCategoryByCategory_Id(@PathVariable("category_id") int category_id){
        return new ResponseEntity<Category>(categoryService.getCategoryByCategory_Id(category_id), HttpStatus.OK);
    }

    // build update category REST API
    // http://localhost:8080/api/langauge/1
    @PutMapping("{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("category_id") int category_id
            ,@RequestBody Category category){
        return new ResponseEntity<Category>(categoryService.updateCategory(category, category_id), HttpStatus.OK);
    }

    // build delete language REST API
    // http://localhost:8080/api/language/1
    @DeleteMapping("{category_id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("category_id") int category_id){

        // delete language from DB
        categoryService.deleteCategory(category_id);

        return new ResponseEntity<String>("Category deleted successfully!.", HttpStatus.OK);
    }













}
