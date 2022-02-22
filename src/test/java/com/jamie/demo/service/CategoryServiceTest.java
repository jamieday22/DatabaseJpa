package com.jamie.demo.service;

import com.jamie.demo.model.Category;
import com.jamie.demo.repository.CategoryRepository;
import com.jamie.demo.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



import static org.assertj.core.api.Assertions.assertThat;

        import com.jamie.demo.exception.ResourceNotFoundException;
        import com.jamie.demo.model.Category;
        import com.jamie.demo.repository.CategoryRepository;
        import com.jamie.demo.service.impl.CategoryServiceImpl;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.BDDMockito.given;
        import static org.mockito.BDDMockito.willDoNothing;
        import static org.mockito.Mockito.*;

        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.util.Collections;
        import java.util.List;
        import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest  {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setup() {
        //categoryRepository = Mockito.mock(CategoryRepository.class);
        //categoryService = new CategoryServiceImpl(categoryRepository);
        category = Category.builder()
                .category_id(1)
                .Name("Anime")
                .build();
    }

    // JUnit test for saveCategory method
    @DisplayName("JUnit test for saveCategory method")
    @Test
    public void givenCategoryObject_whenSaveCategory_thenReturnCategoryObject(){
        // given - precondition or setup
        given(categoryRepository.save(category)).willReturn(category);

        System.out.println(categoryRepository);
        System.out.println(categoryService);

        // when -  action or the behaviour that we are going test
        Category savedCategory = categoryService.saveCategory(category);

        System.out.println(savedCategory);
        // then - verify the output
        assertThat(savedCategory).isNotNull();
    }

    // JUnit test for saveCategory method
//    @DisplayName("JUnit test for saveCategory method which throws exception")
//    @Test
//    public void givenExistingId_whenSaveCategory_thenThrowsException(){
//        // given - precondition or setup
//        given(categoryRepository.findById(category.getCategory_id()))
//                .willReturn(Optional.of(category));
//
//        System.out.println(categoryRepository);
//        System.out.println(categoryService);
//
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            categoryService.saveCategory(category);
//        });
//
//        // then
//        verify(categoryRepository, never()).save(any(Category.class));
//    }

    // JUnit test for getAllCategorys method
    @DisplayName("JUnit test for getAllCategorys method")
    @Test
    public void givenCategorysList_whenGetAllCategorys_thenReturnCategorysList(){
        // given - precondition or setup

        Category category1 = Category.builder()
                .category_id(2)
                .Name("Anime")
                .build();

        given(categoryRepository.findAll()).willReturn(List.of(category,category1));

        // when -  action or the behaviour that we are going test
        List<Category> categoryList = categoryService.getAllCategorys();

        // then - verify the output
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    // JUnit test for getAllCategorys method
    @DisplayName("JUnit test for getAllCategorys method (negative scenario)")
    @Test
    public void givenEmptyCategorysList_whenGetAllCategorys_thenReturnEmptyCategorysList(){
        // given - precondition or setup

        Category category1 = Category.builder()
                .category_id(2)
                .Name("Anime")
                .build();

        given(categoryRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Category> categoryList = categoryService.getAllCategorys();

        // then - verify the output
        assertThat(categoryList).isEmpty();
        assertThat(categoryList.size()).isEqualTo(0);
    }

    // JUnit test for getCategoryById method
    @DisplayName("JUnit test for getCategoryById method")
    @Test
    public void givenCategoryId_whenGetCategoryById_thenReturnCategoryObject(){
        // given
        given(categoryRepository.findById(1)).willReturn(Optional.of(category));

        // when
        Category savedCategory = categoryService.getCategoryById(category.getCategory_id()).get();

        // then
        assertThat(savedCategory).isNotNull();

    }

    // JUnit test for updateCategory method
    @DisplayName("JUnit test for updateCategory method")
    @Test
    public void givenCategoryObject_whenUpdateCategory_thenReturnUpdatedCategory(){
        // given - precondition or setup
        given(categoryRepository.save(category)).willReturn(category);
        category.setCategory_id(1);
        category.setName("Anime");
        // when -  action or the behaviour that we are going test
        Category updatedCategory = categoryService.updateCategory(category);

        // then - verify the output
        assertThat(updatedCategory.getCategory_id()).isEqualTo(1);
        assertThat(updatedCategory.getName()).isEqualTo("Anime");
    }

    // JUnit test for deleteCategory method
    @DisplayName("JUnit test for deleteCategory method")
    @Test
    public void givenCategoryId_whenDeleteCategory_thenNothing(){
        // given - precondition or setup
        int category_id = 1;

        willDoNothing().given(categoryRepository).deleteById(category_id);

        // when -  action or the behaviour that we are going test
        categoryService.deleteCategory(category_id);

        // then - verify the output
        verify(categoryRepository, times(1)).deleteById(category_id);
    }

}
