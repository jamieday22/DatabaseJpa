package com.jamie.demo.repository;

import com.jamie.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
