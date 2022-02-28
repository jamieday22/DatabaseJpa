package com.jamie.demo.controller;

import com.jamie.demo.model.Language;
import com.jamie.demo.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.jamie.demo.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language){
        return languageService.saveLanguage(language);
    }

    @GetMapping
    public List<Language> getAllLanguages(){
        return languageService.getAllLanguages();
    }

    @GetMapping("{language_id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable("language_id") int language_id){
        return languageService.getLanguageById(language_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{language_id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("language_id") int language_id,
                                             @RequestBody Language language){
        return languageService.getLanguageById(language_id)
                .map(savedLanguage -> {

                    savedLanguage.setName(language.getName());



                    Language updatedLanguage = languageService.updateLanguage(savedLanguage);
                    return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{language_id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable("language_id") int language_id){

        languageService.deleteLanguage(language_id);

        return new ResponseEntity<String>("Language deleted successfully!.", HttpStatus.OK);

    }

}
