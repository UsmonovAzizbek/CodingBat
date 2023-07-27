package com.example.codingbat.repository;

import com.example.codingbat.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
}
