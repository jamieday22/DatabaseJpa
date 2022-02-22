package com.jamie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamie.demo.model.Language;
import com.jamie.demo.service.LanguageService;
import com.jamie.demo.service.impl.controller.LanguageController;
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


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(LanguageController.class)
public class LanguageControllerTest   {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenLanguageObject_whenCreateLanguage_thenReturnSavedLanguage() throws Exception{

        // given - precondition or setup
        Language language = Language.builder()
                .Name("Anime")
                .build();
        given(languageService.saveLanguage(any(Language.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/languages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(language)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(language.getName())));



    }

    // JUnit test for Get All languages REST API
    @Test
    public void givenListOfLanguages_whenGetAllLanguages_thenReturnLanguagesList() throws Exception{
        // given - precondition or setup
        List<Language> listOfLanguages = new ArrayList<>();
        listOfLanguages.add(Language.builder().Name("Anime").build());
        listOfLanguages.add(Language.builder().Name("Action").build());
        given(languageService.getAllLanguages()).willReturn(listOfLanguages);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/languages"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfLanguages.size())));

    }

    // positive scenario - valid language id
    // JUnit test for GET language by id REST API
    @Test
    public void givenLanguageId_whenGetLanguageById_thenReturnLanguageObject() throws Exception{
        // given - precondition or setup
        int language_id = 1;
        Language language = Language.builder()
                .Name("Anime")
                .build();
        given(languageService.getLanguageById(language_id)).willReturn(Optional.of(language));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/languages/{language_id}", language_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(language.getName())));


    }

    // negative scenario - valid language id
    // JUnit test for GET language by id REST API
    @Test
    public void givenInvalidLanguageId_whenGetLanguageById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        int language_id = 1;
        Language language = Language.builder()
                .Name("Anime")
                .build();
        given(languageService.getLanguageById(language_id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/languages/{language_id}", language_id));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update language REST API - positive scenario
    @Test
    public void givenUpdatedLanguage_whenUpdateLanguage_thenReturnUpdateLanguageObject() throws Exception{
        // given - precondition or setup
        int language_id = 1;
        Language savedLanguage = Language.builder()
                .Name("Anime")
                .build();

        Language updatedLanguage = Language.builder()
                .Name("Anime")
                .build();
        given(languageService.getLanguageById(language_id)).willReturn(Optional.of(savedLanguage));
        given(languageService.updateLanguage(any(Language.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/languages/{language_id}", language_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedLanguage)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedLanguage.getName())));


    }

    // JUnit test for update language REST API - negative scenario
    @Test
    public void givenUpdatedLanguage_whenUpdateLanguage_thenReturn404() throws Exception{
        // given - precondition or setup
        int language_id = 1;
        Language savedLanguage = Language.builder()
                .Name("Anime")
                .build();

        Language updatedLanguage = Language.builder()
                .Name("Action")
                .build();
        given(languageService.getLanguageById(language_id)).willReturn(Optional.empty());
        given(languageService.updateLanguage(any(Language.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/languages/{language_id}", language_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedLanguage)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete language REST API
    @Test
    public void givenLanguageId_whenDeleteLanguage_thenReturn200() throws Exception{
        // given - precondition or setup
        int language_id = 1;
        willDoNothing().given(languageService).deleteLanguage(language_id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/languages/{language_id}", language_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
