package com.jamie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamie.demo.model.Category;
import com.jamie.demo.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoryController.class)
public class CategoryControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCategoryObject_whenCreateCategory_thenReturnSavedCategory() throws Exception{

        // given - precondition or setup
        Category category = Category.builder()
                .Name("Anime")
                .build();
        given(categoryService.saveCategory(any(Category.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(category.getName())));



    }

    // JUnit test for Get All categorys REST API
    @Test
    public void givenListOfCategorys_whenGetAllCategorys_thenReturnCategorysList() throws Exception{
        // given - precondition or setup
        List<Category> listOfCategorys = new ArrayList<>();
        listOfCategorys.add(Category.builder().Name("Anime").build());
        listOfCategorys.add(Category.builder().Name("Action").build());
        given(categoryService.getAllCategorys()).willReturn(listOfCategorys);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/categorys"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfCategorys.size())));

    }

    // positive scenario - valid category id
    // JUnit test for GET category by id REST API
    @Test
    public void givenCategoryId_whenGetCategoryById_thenReturnCategoryObject() throws Exception{
        // given - precondition or setup
        int category_id = 1;
        Category category = Category.builder()
                .Name("Anime")
                .build();
        given(categoryService.getCategoryById(category_id)).willReturn(Optional.of(category));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/categorys/{category_id}", category_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(category.getName())));


    }

    // negative scenario - valid category id
    // JUnit test for GET category by id REST API
    @Test
    public void givenInvalidCategoryId_whenGetCategoryById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        int category_id = 1;
        Category category = Category.builder()
                .Name("Anime")
                .build();
        given(categoryService.getCategoryById(category_id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/categorys/{category_id}", category_id));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update category REST API - positive scenario
    @Test
    public void givenUpdatedCategory_whenUpdateCategory_thenReturnUpdateCategoryObject() throws Exception{
        // given - precondition or setup
        int category_id = 1;
        Category savedCategory = Category.builder()
                .Name("Anime")
                .build();

        Category updatedCategory = Category.builder()
                .Name("Anime")
                .build();
        given(categoryService.getCategoryById(category_id)).willReturn(Optional.of(savedCategory));
        given(categoryService.updateCategory(any(Category.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/categorys/{category_id}", category_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCategory)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedCategory.getName())));


    }

    // JUnit test for update category REST API - negative scenario
    @Test
    public void givenUpdatedCategory_whenUpdateCategory_thenReturn404() throws Exception{
        // given - precondition or setup
        int category_id = 1;
        Category savedCategory = Category.builder()
                .Name("Anime")
                .build();

        Category updatedCategory = Category.builder()
                .Name("Action")
                .build();
        given(categoryService.getCategoryById(category_id)).willReturn(Optional.empty());
        given(categoryService.updateCategory(any(Category.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/categorys/{category_id}", category_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCategory)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete category REST API
    @Test
    public void givenCategoryId_whenDeleteCategory_thenReturn200() throws Exception{
        // given - precondition or setup
        int category_id = 1;
        willDoNothing().given(categoryService).deleteCategory(category_id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/categorys/{category_id}", category_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}

