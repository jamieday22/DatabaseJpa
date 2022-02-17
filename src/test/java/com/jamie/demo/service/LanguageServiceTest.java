package com.jamie.demo.service;

import com.jamie.demo.repository.LanguageRepository;
import com.jamie.demo.service.impl.LanguageServiceImpl;
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
class LanguageServiceTest {

    @Mock private LanguageRepository languageRepository;
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
    @Disabled
    void saveLanguage() {
    }

    @Test
    @Disabled
    void CanGetLanguageByLanguage_Id() {

    }

    @Test
    @Disabled

    void updateLanguage() {
    }

    @Test
    @Disabled

    void deleteLanguage() {
    }
}