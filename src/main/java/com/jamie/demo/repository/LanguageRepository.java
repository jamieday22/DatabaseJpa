package com.jamie.demo.repository;

import com.jamie.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {


}

