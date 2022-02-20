package com.jamie.demo.service.impl;

import com.jamie.demo.model.Language;
import com.jamie.demo.repository.LanguageRepository;
import com.jamie.demo.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Optional<Language> getLanguageById(int language_id) {
        return languageRepository.findById(language_id);
    }

    @Override
    public Language updateLanguage(Language updatedLanguage) {
        return languageRepository.save(updatedLanguage);
    }

    @Override
    public void deleteLanguage(int language_id) {
        languageRepository.deleteById(language_id);
    }
}