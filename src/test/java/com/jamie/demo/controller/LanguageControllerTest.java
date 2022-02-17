package com.jamie.demo.controller;

import com.jamie.demo.repository.LanguageRepository;
import com.jamie.demo.service.LanguageService;
import com.jamie.demo.service.impl.LanguageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LanguageControllerTest {


    @Mock
    private LanguageRepository languageRepository;
    private LanguageService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LanguageServiceImpl(languageRepository);
    }

    @Test
    void canGetAllLanguage() {
        //when
        underTest.getAllLanguage();
        //then
        verify(languageRepository).findAll();

    }

    @Test
    void saveLanguage() {
    }

    @Test
    void getAllLanguage() {
    }

    @Test
    void getLanguageByLanguage_Id() {
    }

    @Test
    void updateLanguage() {
    }

    @Test
    void deleteLanguage() {
    }
}