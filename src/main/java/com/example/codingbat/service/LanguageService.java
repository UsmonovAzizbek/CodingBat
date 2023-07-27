package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.LanguageDTO;
import com.example.codingbat.entity.Language;
import com.example.codingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    public List<Language> getAllLng(){
        List<Language> repositoryAll = languageRepository.findAll();
        return  repositoryAll;
    }

    public Language getLang(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        Language language = optionalLanguage.get();
        return language;
    }

    public ApiResponse addLang(LanguageDTO languageDTO){
        boolean byName = languageRepository.existsByName(languageDTO.getName());
        if (byName){
            return new ApiResponse("Bunday language avvaldan mavjud", false);
        }
        Language language = new Language();
        language.setName(languageDTO.getName());
        languageRepository.save(language);
        return new ApiResponse("New Language Save", true);
    }

    public ApiResponse delete(Integer id){
        languageRepository.deleteById(id);
        return new ApiResponse("Delete Language", true);
    }

    public ApiResponse update(LanguageDTO languageDTO, Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()){
            Language language = optionalLanguage.get();
            language.setName(languageDTO.getName());
            languageRepository.save(language);
            return new ApiResponse("Update Language", true);
        }
        return new ApiResponse("Not Update Language", false);
    }
}
