package com.jamie.demo.service;

import com.jamie.demo.repository.CategoryRepository;
import com.jamie.demo.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;
    private CategoryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CategoryServiceImpl(categoryRepository);
    }


    @Test
    void canGetAllCategory() {
        //when
        underTest.getAllCategory();
        //then
        verify(categoryRepository).findAll();

    }


    @Test
    @Disabled
    void saveCategory() {
    }

    @Test
    @Disabled
    void CanGetCategoryByCategory_Id() {

    }

    @Test
    @Disabled

    void updateCategory() {
    }

    @Test
    @Disabled

    void deleteCategory() {
    }
}