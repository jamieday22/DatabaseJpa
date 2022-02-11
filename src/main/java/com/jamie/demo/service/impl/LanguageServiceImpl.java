package com.jamie.demo.service.impl;

import java.util.List;

import com.jamie.demo.exception.ResourceNotFoundException;
import com.jamie.demo.model.Language;
import com.jamie.demo.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import com.jamie.demo.service.LanguageService;

@Service  // this is the get post put delete
public class LanguageServiceImpl implements LanguageService {


    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        super();
        this.languageRepository = languageRepository;
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }


    @Override
    public Language getLanguageByLanguage_Id(int language_id) {
//		Optional<Language> language = languageRepository.findById(Language_id);
//		if(language.isPresent()) {
//			return language.get();
//		}else {
//			throw new ResourceNotFoundException("Language", "language_id", language_id);
//		}
        return languageRepository.findById(language_id).orElseThrow(() ->
                new ResourceNotFoundException("Language", "language_id", language_id));

    }

    @Override
    public Language updateLanguage(Language language, int language_id) {

        // we need to check whether language with given id is exists in DB or not
        Language existingLanguage = languageRepository.findById(language_id).orElseThrow(
                () -> new ResourceNotFoundException("Language", "language_id", language_id));

        existingLanguage.setName(language.getName());
//        existingEmployee.setLastName(employee.getLastName());
//        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to DB
        languageRepository.save(existingLanguage);
        return existingLanguage;
    }

    @Override
    public void deleteLanguage(int language_id) {

        // check whether a language exist in a DB or not
        languageRepository.findById(language_id).orElseThrow(() ->
                new ResourceNotFoundException("Language", "language_id", language_id));
        languageRepository.deleteById(language_id);
    }

}
