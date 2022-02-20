package com.jamie.demo.service;

import com.jamie.demo.model.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    Language saveLanguage(Language language);
    List<Language> getAllLanguages();
    Optional<Language> getLanguageById(int language_id);
    Language updateLanguage(Language updatedLanguage);
    void deleteLanguage(int language_id);
}