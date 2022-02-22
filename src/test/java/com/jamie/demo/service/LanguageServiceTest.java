package com.jamie.demo.service;

import com.jamie.demo.model.Language;
import com.jamie.demo.repository.LanguageRepository;
import com.jamie.demo.service.impl.LanguageServiceImpl;
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



import com.jamie.demo.model.Language;
        import com.jamie.demo.repository.LanguageRepository;
        import com.jamie.demo.service.impl.LanguageServiceImpl;
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
        import com.jamie.demo.model.Language;
        import com.jamie.demo.repository.LanguageRepository;
        import com.jamie.demo.service.impl.LanguageServiceImpl;
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
public class LanguageServiceTest   {

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    private Language language;

    @BeforeEach
    public void setup() {
        //languageRepository = Mockito.mock(LanguageRepository.class);
        //languageService = new LanguageServiceImpl(languageRepository);
        language = Language.builder()
                .language_id(1)
                .Name("Anime")
                .build();
    }

    // JUnit test for saveLanguage method
    @DisplayName("JUnit test for saveLanguage method")
    @Test
    public void givenLanguageObject_whenSaveLanguage_thenReturnLanguageObject(){
        // given - precondition or setup
        given(languageRepository.save(language)).willReturn(language);

        System.out.println(languageRepository);
        System.out.println(languageService);

        // when -  action or the behaviour that we are going test
        Language savedLanguage = languageService.saveLanguage(language);

        System.out.println(savedLanguage);
        // then - verify the output
        assertThat(savedLanguage).isNotNull();
    }

    // JUnit test for saveLanguage method
//    @DisplayName("JUnit test for saveLanguage method which throws exception")
//    @Test
//    public void givenExistingId_whenSaveLanguage_thenThrowsException(){
//        // given - precondition or setup
//        given(languageRepository.findById(language.getLanguage_id()))
//                .willReturn(Optional.of(language));
//
//        System.out.println(languageRepository);
//        System.out.println(languageService);
//
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            languageService.saveLanguage(language);
//        });
//
//        // then
//        verify(languageRepository, never()).save(any(Language.class));
//    }

    // JUnit test for getAllLanguages method
    @DisplayName("JUnit test for getAllLanguages method")
    @Test
    public void givenLanguagesList_whenGetAllLanguages_thenReturnLanguagesList(){
        // given - precondition or setup

        Language language1 = Language.builder()
                .language_id(2)
                .Name("Anime")
                .build();

        given(languageRepository.findAll()).willReturn(List.of(language,language1));

        // when -  action or the behaviour that we are going test
        List<Language> languageList = languageService.getAllLanguages();

        // then - verify the output
        assertThat(languageList).isNotNull();
        assertThat(languageList.size()).isEqualTo(2);
    }

    // JUnit test for getAllLanguages method
    @DisplayName("JUnit test for getAllLanguages method (negative scenario)")
    @Test
    public void givenEmptyLanguagesList_whenGetAllLanguages_thenReturnEmptyLanguagesList(){
        // given - precondition or setup

        Language language1 = Language.builder()
                .language_id(2)
                .Name("Anime")
                .build();

        given(languageRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Language> languageList = languageService.getAllLanguages();

        // then - verify the output
        assertThat(languageList).isEmpty();
        assertThat(languageList.size()).isEqualTo(0);
    }

    // JUnit test for getLanguageById method
    @DisplayName("JUnit test for getLanguageById method")
    @Test
    public void givenLanguageId_whenGetLanguageById_thenReturnLanguageObject(){
        // given
        given(languageRepository.findById(1)).willReturn(Optional.of(language));

        // when
        Language savedLanguage = languageService.getLanguageById(language.getLanguage_id()).get();

        // then
        assertThat(savedLanguage).isNotNull();

    }

    // JUnit test for updateLanguage method
    @DisplayName("JUnit test for updateLanguage method")
    @Test
    public void givenLanguageObject_whenUpdateLanguage_thenReturnUpdatedLanguage(){
        // given - precondition or setup
        given(languageRepository.save(language)).willReturn(language);
        language.setLanguage_id(1);
        language.setName("Anime");
        // when -  action or the behaviour that we are going test
        Language updatedLanguage = languageService.updateLanguage(language);

        // then - verify the output
        assertThat(updatedLanguage.getLanguage_id()).isEqualTo(1);
        assertThat(updatedLanguage.getName()).isEqualTo("Anime");
    }

    // JUnit test for deleteLanguage method
    @DisplayName("JUnit test for deleteLanguage method")
    @Test
    public void givenLanguageId_whenDeleteLanguage_thenNothing(){
        // given - precondition or setup
        int language_id = 1;

        willDoNothing().given(languageRepository).deleteById(language_id);

        // when -  action or the behaviour that we are going test
        languageService.deleteLanguage(language_id);

        // then - verify the output
        verify(languageRepository, times(1)).deleteById(language_id);
    }

}
