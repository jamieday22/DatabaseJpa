package com.jamie.demo.service;

import com.jamie.demo.model.Language;

import java.util.List;



public interface LanguageService {
    Language saveLanguage(Language language);
    List<Language> getAllLanguage();
    Language getLanguageByLanguage_Id(int language_id);

    Language updateLanguage(Language language, int language_id);
    void deleteLanguage(int language_id);
}