package com.jamie.demo.controller;

import java.util.List;

import com.jamie.demo.model.Language;
import com.jamie.demo.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        super();
        this.languageService = languageService;
    }

    // build create language REST API
    @PostMapping()
    public ResponseEntity<Language> saveLanguage(@RequestBody Language language){
        return new ResponseEntity<Language>(languageService.saveLanguage(language), HttpStatus.CREATED);
    }

    // build get all language REST API
    @GetMapping
    public List<Language> getAllLanguage(){
        return languageService.getAllLanguage();
    }

    // build get language by id REST API
    // http://localhost:8080/api/langauge/1
    @GetMapping("{language_id}")
    public ResponseEntity<Language> getLanguageByLanguage_Id(@PathVariable("language_id") int language_id){
        return new ResponseEntity<Language>(languageService.getLanguageByLanguage_Id(language_id), HttpStatus.OK);
    }

    // build update langauge REST API
    // http://localhost:8080/api/langauge/1
    @PutMapping("{language_id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("language_id") int language_id
            ,@RequestBody Language language){
        return new ResponseEntity<Language>(languageService.updateLanguage(language, language_id), HttpStatus.OK);
    }

    // build delete language REST API
    // http://localhost:8080/api/language/1
    @DeleteMapping("{language_id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable("language_id") int language_id){

        // delete language from DB
        languageService.deleteLanguage(language_id);

        return new ResponseEntity<String>("Language deleted successfully!.", HttpStatus.OK);
    }

}